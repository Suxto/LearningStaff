import matplotlib.pyplot as plt
import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader
from torchvision import datasets, transforms
from tqdm import tqdm

from conf import batch_size, input_dim, output_dim, num_epochs, data_path, device, num_classes
from model import Generator, Discriminator

# 设置随机种子
torch.manual_seed(42)

# 加载MNIST数据集
transform = transforms.Compose([
    transforms.ToTensor(),
    transforms.Normalize((0.5,), (0.5,))
])

train_dataset = datasets.MNIST(root=data_path, train=True, transform=transform, download=True)
train_loader = DataLoader(train_dataset, batch_size=batch_size, shuffle=True)

# 初始化生成器和判别器
generator = Generator(input_dim, output_dim, num_classes).to(device)
discriminator = Discriminator(output_dim, num_classes).to(device)

# 定义损失函数和优化器
criterion = nn.BCELoss()
optimizer_g = optim.Adam(generator.parameters(), lr=0.0002, betas=(0.5, 0.999))
optimizer_d = optim.Adam(discriminator.parameters(), lr=0.0002, betas=(0.5, 0.999))

# 定义用于可视化训练损失的列表
losses = []

# 训练GAN
for epoch in range(1, num_epochs + 1, 1):
    progress_bar = tqdm(train_loader, total=len(train_loader))
    for batch_idx, (real_images, labels) in enumerate(progress_bar):
        real_images = real_images.view(-1, output_dim).to(device)
        labels = labels.to(device)
        batch_size = real_images.size(0)

        # 训练判别器
        discriminator.zero_grad()
        labels_real = torch.ones(batch_size, 1).to(device)
        labels_fake = torch.zeros(batch_size, 1).to(device)

        # 向生成器输入随机噪声
        noise = torch.randn(batch_size, input_dim).to(device)
        fake_images = generator(noise, labels)

        # 使用真实图像计算以下是对判别器和生成器的训练部分的代码：

        # 使用真实图像计算判别器损失
        outputs_real = discriminator(real_images, labels)
        d_loss_real = criterion(outputs_real, labels_real)

        # 使用生成的图像计算判别器损失
        outputs_fake = discriminator(fake_images.detach(), labels)
        d_loss_fake = criterion(outputs_fake, labels_fake)

        # 合并判别器损失
        d_loss = d_loss_real + d_loss_fake
        d_loss.backward()
        optimizer_d.step()

        # 训练生成器
        generator.zero_grad()
        outputs = discriminator(fake_images, labels)
        g_loss = criterion(outputs, labels_real)
        g_loss.backward()
        optimizer_g.step()

        # 记录损失值
        if batch_idx % 100 == 0:
            losses.append((d_loss.item(), g_loss.item()))
        progress_bar.set_description(f"Epoch [{epoch}/{num_epochs}], "
                                     f"Discriminator Loss: {d_loss.item():.4f}, "
                                     f"Generator Loss: {g_loss.item():.4f}")
    if epoch % 10 == 0:
        torch.save(generator.state_dict(), model_path('generator', epoch))

# 可视化训练损失
plt.figure(figsize=(10, 5))
plt.plot(losses)
plt.title("Training Loss")
plt.xlabel("Iterations")
plt.ylabel("Loss")
plt.show()

torch.save(discriminator.state_dict(), model_path('discriminator'))
# torch.save(generator.state_dict(), model_path('generator'))
