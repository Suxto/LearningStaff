import torch
import torch.nn as nn
import torch.optim as optim
import torchvision
import torchvision.transforms as transforms
from torch.utils.data import DataLoader

from tqdm import tqdm

device = (
    "cuda"
    if torch.cuda.is_available()
    else "cpu"
)
print(f"Using {device} device")

# 超参数
batch_size = 64
num_epochs = 10
learning_rate = 0.001
train = True
data_path = '../../Data'

# 加载MNIST数据集并进行预处理
transform = transforms.Compose([
    transforms.ToTensor(),
    transforms.Normalize((0.5,), (0.5,))
])

train_dataset = torchvision.datasets.MNIST(root=data_path, train=True, download=True, transform=transform)
test_dataset = torchvision.datasets.MNIST(root=data_path, train=False, download=True, transform=transform)

train_loader = torch.utils.data.DataLoader(train_dataset, batch_size=batch_size, shuffle=True)
test_loader = torch.utils.data.DataLoader(test_dataset, batch_size=batch_size, shuffle=False)


class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.flatten = nn.Flatten()  # 展平图像
        self.fc1 = nn.Linear(28 * 28, 45)
        self.relu1 = nn.ReLU()
        self.fc2 = nn.Linear(45, 20)
        self.relu2 = nn.ReLU()
        self.out = nn.Linear(20, 10)

    def forward(self, x):
        x = self.flatten(x)
        x = self.fc1(x)
        x = self.relu1(x)
        x = self.fc2(x)
        x = self.relu2(x)
        x = self.out(x)
        return x


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
    model.load_state_dict(torch.load('model.pth'))

# 在测试集上评估模型
model.eval()
with torch.no_grad():  # 关闭模型的反向传播过程
    correct = 0
    total = 0
    for images, labels in test_loader:
        images = images.to(device)
        labels = labels.to(device)

        outputs = model(images)
        _, predicted = torch.max(outputs.data, 1)
        total += labels.size(0)
        correct += (predicted == labels).sum().item()
    print('在测试集上的准确率为: {} %'.format(100 * correct / total))
