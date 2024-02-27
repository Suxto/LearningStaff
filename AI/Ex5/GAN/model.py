import torch
import torch.nn as nn
from conf import device


# 定义生成器网络
class Generator(nn.Module):
    def __init__(self, input_dim, output_dim, num_classes):
        super(Generator, self).__init__()
        self.fc = nn.Sequential(
            nn.Linear(input_dim + num_classes, 256),
            nn.ReLU(),
            nn.Linear(256, output_dim),
            nn.Tanh()
        )
        self.num_classes = num_classes

    def forward(self, x, labels):
        # 将条件类别标签转换为One-Hot编码
        labels_onehot = torch.zeros(labels.size(0), self.num_classes).to(device)
        labels_onehot.scatter_(1, labels.view(-1, 1), 1)

        # 将噪声向量和条件类别标签进行拼接
        x = torch.cat([x, labels_onehot], dim=1)
        x = self.fc(x)
        return x


# 定义判别器网络
class Discriminator(nn.Module):
    def __init__(self, input_dim, num_classes):
        super(Discriminator, self).__init__()
        self.fc = nn.Sequential(
            nn.Linear(input_dim + num_classes, 256),
            nn.LeakyReLU(0.2),
            nn.Linear(256, 1),
            nn.Sigmoid()
        )
        self.num_classes = num_classes

    def forward(self, x, labels):
        # 将条件类别标签转换为One-Hot编码
        labels_onehot = torch.zeros(labels.size(0), self.num_classes).to(device)
        labels_onehot.scatter_(1, labels.view(-1, 1), 1)

        # 将图像数据和条件类别标签进行拼接
        x = torch.cat([x, labels_onehot], dim=1)
        x = self.fc(x)
        return x
