import torch.nn.functional as fun
from matplotlib import pyplot as plt

from conf import *
from model import Generator

# 创建生成器模型实例
generator = Generator(input_dim, output_dim, num_classes).to(device)


def gen_all_digits(sequence, num_models):
    num_samples = len(sequence)
    fig, axs = plt.subplots(num_models, num_samples + 1, figsize=(num_samples + 1, num_models))

    for i, idx in enumerate(range(0, 110, 10)):
        generator.load_state_dict(torch.load(model_path('generator', idx), map_location=device))
        generator.eval()  # 关闭梯度下降
        # 生成随机噪声
        fixed_noise = torch.randn(num_samples, input_dim).to(device)
        fixed_labels = torch.tensor(sequence).to(device)
        generated_images = generator(fixed_noise, fixed_labels).detach().cpu()

        # 标签
        axs[i, 0].text(0.5, 0.5, f"{idx} epoch", horizontalalignment='center', verticalalignment='center',
                       transform=axs[i, 0].transAxes, fontsize=13)
        axs[i, 0].axis('off')

        for j in range(num_samples):
            axs[i, j + 1].imshow(generated_images[j].view(28, 28), cmap='gray')
            axs[i, j + 1].axis('off')

    # 调整子图之间的间距
    plt.subplots_adjust(hspace=0.2, wspace=0.15)
    plt.show()


gen_all_digits([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], 11)
