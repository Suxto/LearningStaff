import torch
import torch.nn as nn
import matplotlib.pyplot as plt


import data_utils
import engine
import config
import test


def save_loss_plot(
    OUT_DIR,
    train_loss_list,
    x_label="iterations",
    y_label="train loss",
    save_name="train_loss_iter",
):
    """
    Function to save both train loss graph.

    :param OUT_DIR: Path to save the graphs.
    :param train_loss_list: List containing the training loss values.
    """
    figure_1 = plt.figure(figsize=(10, 7), num=1, clear=True)
    train_ax = figure_1.add_subplot()
    train_ax.plot(train_loss_list, color="tab:blue")
    train_ax.set_xlabel(x_label)
    train_ax.set_ylabel(y_label)
    figure_1.savefig(f"{OUT_DIR}/{save_name}.png")
    print("SAVING PLOTS COMPLETE...")
    # plt.close('all')


if __name__ == "__main__":
    torch.multiprocessing.freeze_support()
    device = config.device
    torch.backends.cudnn.enabled = False
    torch.backends.cudnn.benchmark = False
    # 加载训练数据集
    train_dataset = data_utils.create_train_dataset(
        config.train_img_dir,
        config.train_xml_dir,
        config.img_format,
        config.img_width,
        config.img_height,
        config.CLASSES,
    )
    train_dataloader = data_utils.create_train_loader(train_dataset, 1)

    # 训练模型
    model = config.get_model()
    model.to(device)
    optimizer = torch.optim.SGD(
        model.parameters(), lr=config.lr, momentum=0.9, weight_decay=0.0005
    )
    criterion = nn.CrossEntropyLoss()
    train_loss_hist = engine.Averager()

    best_mAP = 0

    # Train and validation loss lists to store loss values of all
    # iterations till ena and plot graphs for all iterations.
    train_loss_list = []
    loss_cls_list = []
    loss_box_reg_list = []
    loss_objectness_list = []
    loss_rpn_list = []
    train_loss_list_epoch = []
    mAP_list = []

    for epoch in range(config.num_epoch):
        train_loss_hist.reset()
        (
            batch_loss_list,
            batch_loss_cls_list,
            batch_loss_box_reg_list,
            batch_loss_objectness_list,
            batch_loss_rpn_list,
        ) = engine.train_one_epoch(
            model,
            optimizer,
            train_dataloader,
            device,
            epoch,
            config.num_epoch,
            train_loss_hist,
        )

        mAP = 0
        for class_idx in range(1, config.num_classes):
            prec, rec, ap = test.eval_on_test(model, class_idx)
            mAP += ap
        mAP /= config.num_classes - 1
        mAP_list.append(mAP)

        # Append the current epoch's batch-wise losses to the `train_loss_list`.
        train_loss_list.extend(batch_loss_list)
        loss_cls_list.extend(batch_loss_cls_list)
        loss_box_reg_list.extend(batch_loss_box_reg_list)
        loss_objectness_list.extend(batch_loss_objectness_list)
        loss_rpn_list.extend(batch_loss_rpn_list)
        # Append curent epoch's average loss to `train_loss_list_epoch`.
        train_loss_list_epoch.append(train_loss_hist.value)

        # Save loss plot for batch-wise list.
        save_loss_plot(config.OUT_DIR, train_loss_list)
        # Save loss plot for epoch-wise list.
        save_loss_plot(
            config.OUT_DIR,
            train_loss_list_epoch,
            "epochs",
            "train loss",
            save_name="train_loss_epoch",
        )
        save_loss_plot(
            config.OUT_DIR,
            loss_cls_list,
            "iterations",
            "loss cls",
            save_name="loss_cls",
        )
        save_loss_plot(
            config.OUT_DIR,
            loss_box_reg_list,
            "iterations",
            "loss bbox reg",
            save_name="loss_bbox_reg",
        )
        save_loss_plot(
            config.OUT_DIR,
            loss_objectness_list,
            "iterations",
            "loss obj",
            save_name="loss_obj",
        )
        save_loss_plot(
            config.OUT_DIR,
            loss_rpn_list,
            "iterations",
            "loss rpn bbox",
            save_name="loss_rpn_bbox",
        )
        save_loss_plot(
            config.OUT_DIR,
            mAP_list,
            "epochs",
            "mAP",
            save_name="mAP_on_eval",
        )

        # 保存最新模型和最佳模型
        torch.save(model.state_dict(), config.get_lastest_model_path())
        if mAP > best_mAP:
            best_mAP = mAP
            torch.save(model.state_dict(), config.get_best_model_path())
