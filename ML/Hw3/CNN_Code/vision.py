import matplotlib.pyplot as plt


def plot_feature(model, image_index, images):
    # 获取第一个和第二个卷积层的输出
    conv1_output = model.conv1(images)
    conv2_output = model.conv2(conv1_output)

    # 可视化第一个卷积层的输出
    fig, axs = plt.subplots(nrows=4, ncols=4, figsize=(10, 10))
    for i, ax in enumerate(axs.flatten()):
        ax.imshow(conv1_output[image_index, i].cpu().detach().numpy(), cmap='gray')
        ax.axis('off')
    plt.suptitle('Conv1 Output')
    plt.show()

    # 可视化第二个卷积层的输出
    fig, axs = plt.subplots(nrows=4, ncols=8, figsize=(10, 5))
    for i, ax in enumerate(axs.flatten()):
        ax.imshow(conv2_output[image_index, i].cpu().detach().numpy(), cmap='gray')
        ax.axis('off')
    plt.suptitle('Conv2 Output')
    plt.show()
