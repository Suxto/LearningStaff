import os
import torch
import data_utils
import config
import cv2
import torchvision
import matplotlib.pyplot as plt
import numpy as np
from tqdm import tqdm

device = config.device

# 加载测试数据集
test_dataset = data_utils.create_valid_dataset(
    config.test_img_dir,
    config.test_xml_dir,
    config.img_format,
    config.img_width,
    config.img_height,
    config.CLASSES,
)

test_dataloader = data_utils.create_valid_loader(test_dataset, 1)


def plot_RP(prec, rec, name):
    plt.figure()
    plt.plot(rec, prec)
    plt.xlabel("Recall")
    plt.ylabel("Precision")
    plt.tight_layout()
    plt.savefig(os.path.join(config.OUT_DIR, name))


# 在测试集上验证
def eval_on_test(
    model, class_idx, iou_threshold=0.5, show_img=False, mark_img=False, plot_rp=False
):
    model.eval()

    if not show_img and mark_img:
        output_path = os.path.join(config.OUT_DIR, "test_out")
        if not os.path.exists(output_path):
            os.mkdir(output_path)

    # 真正类，假正类，置信度
    list_tp, list_fp, list_conf = [], [], []
    # GT个数
    num_gt = 0

    for i, (images, targets) in enumerate(tqdm(test_dataloader, desc="Evaluating: ")):
        images = list(image.to(device) for image in images)
        targets = [{k: v.to(device) for k, v in t.items()} for t in targets]

        with torch.no_grad():
            outputs = model(images)

        for j, (image, target) in enumerate(zip(images, targets)):
            # img = image.permute(1, 2, 0).byte().cpu().numpy()
            img = (image.permute(1, 2, 0) * 255).byte().cpu().numpy()
            if len(target["boxes"]) == 0:
                continue

            cur_class_ind = target["labels"] == class_idx
            anno_boxes = target["boxes"][cur_class_ind]

            num_gt += len(anno_boxes)

            pred_boxes, confs, pred_labels = (
                outputs[j]["boxes"],
                outputs[j]["scores"],
                outputs[j]["labels"],
            )

            cur_class_ind = pred_labels == class_idx

            pred_boxes = pred_boxes[cur_class_ind]
            confs = confs[cur_class_ind]

            confs, indices = torch.sort(confs, descending=True)
            pred_boxes = pred_boxes[indices]

            iou = torchvision.ops.box_iou(pred_boxes, anno_boxes)
            iou = iou.cpu().numpy()

            # 是否第一次标记GT
            gt_vis = [False] * len(anno_boxes)
            confs = confs.cpu()
            for areas, conf in zip(iou, confs):
                # 当前box的GT下标
                gt_idx = np.argmax(areas)
                if areas[gt_idx] >= iou_threshold and gt_vis[gt_idx] is False:
                    # TP=1, FP=0
                    gt_vis[gt_idx] = True
                    list_tp.append(1)
                    list_fp.append(0)
                else:
                    # TP=0, FP=1
                    list_tp.append(0)
                    list_fp.append(1)
                list_conf.append(conf)

            # 在图片上标记
            if mark_img:
                # 标注框和预测框图片
                img_with_anno = img.copy()
                img_with_pred = img.copy()
                for box in target["boxes"]:
                    x1, y1, x2, y2 = [int(coord.item()) for coord in box]
                    cv2.rectangle(img_with_anno, (x1, y1), (x2, y2), (255, 0, 0), 2)

                for box, conf, label in zip(pred_boxes, confs, pred_labels):
                    x1, y1, x2, y2 = [int(coord.item()) for coord in box]
                    cv2.rectangle(img_with_pred, (x1, y1), (x2, y2), (0, 255, 0), 2)
                    cv2.putText(
                        img_with_pred,
                        f"{config.CLASSES[label.item()]}: {conf.item():.2f}",
                        # f"{conf.item():.2f}",
                        (x1, y1 - 10),
                        cv2.FONT_HERSHEY_SIMPLEX,
                        0.5,
                        (36, 255, 12),
                        2,
                    )

                # 将两个图像水平并排显示
                img_height, img_width, _ = img.shape
                combined_width = img_width * 2 + 15  # 增加图像间隔
                img = np.zeros((img_height, combined_width, 3), dtype=np.uint8)
                img[:, :img_width] = img_with_anno
                img[:, img_width + 15 :] = img_with_pred

                if show_img:
                    # 展示图像
                    cv2.imshow(f"Prediction on class {config.CLASSES[class_idx]}", img)
                    cv2.waitKey(0)
                    cv2.destroyAllWindows()
                else:
                    # 保存图像
                    output_path = os.path.join(
                        config.OUT_DIR, "test_out", f"image_{i}_{j}.png"
                    )
                    cv2.imwrite(output_path, img)

    list_conf = np.array(list_conf)
    ind = list_conf.argsort()
    ind = ind[::-1]
    # print(list_conf[ind])
    # list_conf = list_conf[ind]
    list_tp = np.array(list_tp)[ind]
    list_fp = np.array(list_fp)[ind]
    # 查准率，查全率
    prec, rec = [], []
    tp, fp = 0, 0
    for cur_tp, cur_fp in zip(list_tp, list_fp):
        tp += cur_tp
        fp += cur_fp
        prec.append(tp / (tp + fp))
        rec.append(tp / num_gt)

    if plot_rp:
        plot_RP(prec, rec, "AP")

    # correct AP calculation
    # first append sentinel values at the end
    mrec = np.concatenate(([0.0], rec, [1.0]))
    mpre = np.concatenate(([0.0], prec, [0.0]))

    # compute the precision envelope
    for i in range(mpre.size - 1, 0, -1):
        mpre[i - 1] = np.maximum(mpre[i - 1], mpre[i])

    # to calculate area under PR curve, look for points
    # where X axis (recall) changes value
    i = np.where(mrec[1:] != mrec[:-1])[0]

    # and sum (\Delta recall) * prec
    ap = np.sum((mrec[i + 1] - mrec[i]) * mpre[i + 1])

    return prec, rec, ap


if __name__ == "__main__":
    model = config.get_model()
    model.load_state_dict(torch.load(config.get_best_model_path(), map_location=device))
    model.to(device)
    mAP = 0
    for class_idx in range(1, config.num_classes):
        prec, rec, ap = eval_on_test(model, class_idx, plot_rp=True, mark_img=True)
        mAP += ap
    mAP /= config.num_classes - 1
    print(f"mAP = {mAP}")
