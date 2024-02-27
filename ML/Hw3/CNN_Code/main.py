import netron
import torch
import torch.nn as nn
import torch.optim as optim
import torchvision
import torchvision.transforms as transforms
from torch.utils.data import DataLoader

from tqdm import tqdm

from vision import plot_feature

device = (
    "cuda"
    if torch.cuda.is_available()
    else "cpu"
)

print(f"Using {device} device")

# 定义超参数
batch_size = 64  # 一批64个
num_epochs = 10  # 10个循环
learning_rate = 0.001  # 学习率
train = False
test = False
show_model = False
# 加载MNIST数据集并进行预处理
transform = transforms.Compose([
    transforms.ToTensor(),
    transforms.Normalize((0.5,), (0.5,))
])

train_dataset = torchvision.datasets.MNIST(root='./data', train=True, download=True, transform=transform)
test_dataset = torchvision.datasets.MNIST(root='./data', train=False, download=True, transform=transform)

train_loader = torch.utils.data.DataLoader(train_dataset, batch_size=batch_size, shuffle=True)
test_loader = torch.utils.data.DataLoader(test_dataset, batch_size=batch_size, shuffle=False)


# 定义CNN模型
class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.conv1 = nn.Sequential(  # (1, 28, 28)
            nn.Conv2d(
                in_channels=1,
                out_channels=16,
                kernel_size=5,
                stride=1,
                padding=2,
            ),  # (16, 28, 28)
            nn.ReLU(),
            nn.MaxPool2d(kernel_size=2),  # (16, 14, 14)
        )
        self.conv2 = nn.Sequential(  # (16, 14, 14)
            nn.Conv2d(16, 32, 5, 1, 2),  # (32, 14, 14)
            nn.ReLU(),
            nn.MaxPool2d(2),  # (32, 7, 7)
        )
        self.out = nn.Linear(32 * 7 * 7, 10)  # fc layer, output 10 classes

    def forward(self, x):
        x = self.conv1(x)
        x = self.conv2(x)
        x = x.view(x.size(0), -1)  # 拉直进入全连接层 (batch_size, 32 * 7 * 7)
        output = self.out(x)
        return output


# 实例化模型，并将模型移动到显卡
model = Net().to(device)

# 定义损失函数和优化器
criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=learning_rate)

data_tier = DataLoader(train_loader.dataset, batch_size=train_loader.batch_size, shuffle=True)
if train:  # 训练模型
    total_step = len(data_tier)
    for epoch in range(num_epochs):
        progress_bar = tqdm(data_tier, total=total_step)  # 使用 tqdm 创建进度条
        for i, (images, labels) in enumerate(progress_bar):
            images = images.to(device)
            labels = labels.to(device)
            model.zero_grad()
            # 向前传播
            outputs = model(images)
            loss = criterion(outputs, labels)

            # 反向传播和优化
            optimizer.zero_grad()
            loss.backward()
            optimizer.step()

            if (i + 1) % 100 == 0:
                progress_bar.set_description('Epoch [{}/{}], Loss: {:.4f}'
                                             .format(epoch + 1, num_epochs, loss.item()))
    # 保存模型
    torch.save(model.state_dict(), 'model.pth')

if not train:  # 如果不训练的话，载入模型
    model = Net()
    model.load_state_dict(torch.load('model.pth', map_location=device))

if show_model:
    data = torch.randn(batch_size, 1, 28, 28)
    # torch.onnx.export(model, data, 'model.onnx')
    # netron.start('model.onnx')

plot = True  # 画图
S = set()
if test:
    # 在测试集上评估模型
    model.eval()
    with torch.no_grad():  # 关闭模型的反向传播过程
        correct = 0
        total = 0
        for images, labels in test_loader:
            images = images.to(device)
            labels = labels.to(device)

            outputs = model(images)
            # outputs = torch.softmax(outputs, dim=1)  # 使用 softmax 函数优化输出
            _, predicted = torch.max(outputs.data, 1)
            total += labels.size(0)
            correct += (predicted == labels).sum().item()
            if labels[0].item() not in S and plot:
                S.add(labels[0].item())
                # print(f'{labels[0]} -> {predicted[0]}')
                # print(predicted)
                plot_feature(model, 0, images)  # 可视化输出
        print('在测试集上的准确率为: {} %'.format(100 * correct / total))
