import numpy as np
from sklearn.mixture import GaussianMixture
import matplotlib.pyplot as plt
from matplotlib import rcParams

# 设置中文字体
rcParams["font.family"] = "SimHei"
# 解决负号问题
rcParams["axes.unicode_minus"] = False
# 定义数据集目录
directory = "data/"


# 聚类并展示结果
def gmm_and_show(file, num_class):
    data = np.loadtxt(directory + file)
    # 创建并拟合 GMM 模型
    gmm = GaussianMixture(n_components=num_class)
    gmm.fit(data)
    # 输出每个样本的所属聚类
    labels = gmm.predict(data)
    plt.figure()
    # 分类绘制聚类结果
    for label in np.unique(labels):
        ind = labels == label
        plt.scatter(data[ind, 0], data[ind, 1])

    plt.title(f"数据集{file}使用GMM聚类")
    print(f"数据集{file}使用GMM聚类")


# 测试函数
if __name__ == "__main__":
    gmm_and_show("aniso.txt", 3)
    gmm_and_show("blobs.txt", 3)
    gmm_and_show("no_structure.txt", 1)
    gmm_and_show("noisy_circle.txt", 2)
    gmm_and_show("noisy_moons.txt", 2)
    gmm_and_show("varied.txt", 3)

    plt.tight_layout()
    plt.show()
