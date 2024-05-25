import os
from torchvision.models.detection import (
    fasterrcnn_resnet50_fpn_v2,
    FasterRCNN_ResNet50_FPN_V2_Weights,
)
import torch
from torchvision.models.detection.faster_rcnn import FastRCNNPredictor, FasterRCNN


def in_blue(s):
    return "\033[94m" + str(s) + "\033[0m"


def in_red(s):
    return "\033[91m" + str(s) + "\033[0m"


# 参数
num_epoch = 150
lr = 0.001

# 定义类别
CLASSES = ["__background__", "mitosis"]
num_classes = len(CLASSES)  # 背景类+类别数

# 图片缩放
img_width = 800  # 1663
img_height = 700  # 1485
# 图片格式
img_format = ".png"

# 数据存放位置
data_path = "C:\\Users\\suxto\\Downloads\\data"
# 输出文件保存位置
OUT_DIR = "C:\\Users\\suxto\\Downloads\\RCNN_Out"
# 训练数据集
train_img_dir = os.path.join(data_path, "train", "img")
train_xml_dir = os.path.join(data_path, "train", "xml")
# 测试数据集
test_img_dir = os.path.join(data_path, "test", "img")
test_xml_dir = os.path.join(data_path, "test", "xml")


device = torch.device("cuda") if torch.cuda.is_available() else torch.device("cpu")
print(in_blue(f"using {device}"))


if not os.path.exists(data_path):
    print(in_red(f"Data path not exist!({data_path})"))
    exit(1)

if not os.path.exists(OUT_DIR):
    os.mkdir(OUT_DIR)

# 保存模型名称
latest_modle_name = "faster_rcnn_model_latest"
best_modle_name = "faster_rcnn_model_best"
modle_suffix = ".pth"


# 创建 Fast RCNN 模型
def get_model():
    weights = FasterRCNN_ResNet50_FPN_V2_Weights.DEFAULT
    model: FasterRCNN = fasterrcnn_resnet50_fpn_v2(weights=weights, pretrained=True)
    in_features = model.roi_heads.box_predictor.cls_score.in_features
    model.roi_heads.box_predictor = FastRCNNPredictor(in_features, num_classes)
    return model


def get_lastest_model_path(num=None):
    if num:
        return os.path.join(OUT_DIR, latest_modle_name + num + modle_suffix)
    else:
        return os.path.join(OUT_DIR, latest_modle_name + modle_suffix)


def get_best_model_path(num=None):
    if num:
        return os.path.join(OUT_DIR, best_modle_name + num + modle_suffix)
    else:
        return os.path.join(OUT_DIR, best_modle_name + modle_suffix)
