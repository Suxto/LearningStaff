import numpy as np
import matplotlib.pyplot as plt
import os
from matplotlib import rcParams


# 设置中文字体
rcParams["font.family"] = "SimHei"
# 解决负号问题
rcParams["axes.unicode_minus"] = False
# 定义数据集目录
directory = "data/"


# from sklearn.metrics import silhouette_score, calinski_harabasz_score


# 欧式距离聚类
def Euclidean_Distance(centers, data):
    dist = np.zeros((data.shape[0], centers.shape[0]))
    # dist= sqrt(sum((x-center)^2))
    for idx, center in enumerate(centers):
        dist[:, idx] = np.sqrt(np.sum((data - center) ** 2, axis=1))
    # 根据最小距离选择类别
    return np.argmin(dist, axis=1)


# 曼哈顿距离聚类
def Manhattan_Distance(centers, data):
    dist = np.zeros((data.shape[0], centers.shape[0]))
    # dist= sum(|x-center|)
    for idx, center in enumerate(centers):
        dist[:, idx] = np.sum(np.abs(data - center), axis=1)
    # 根据最小距离选择类别
    return np.argmin(dist, axis=1)


# 余弦相似度
def Cosine_Similarity(centers, data):
    # 分别计算中心点和样本的模长
    mag_center = np.sqrt(np.sum(centers**2, axis=1))
    mag_data = np.sqrt(np.sum(data**2, axis=1))
    # 计算余弦相似度
    dot_product = data @ centers.T
    dist = dot_product / np.outer(mag_data, mag_center)
    # 选择最接近1的类别
    return np.argmin(np.abs(dist - 1), axis=1)


# K-Means聚类函数，计算聚类中心
def kmenas_train(data, dist_fun, k):
    # 选择前k个作为聚类中心
    centers = np.array(data[:k])
    while True:
        center_new = np.zeros(centers.shape)
        # 根据聚类中心计算每个样本属于的类别
        res = dist_fun(centers, data)
        # 计算新的聚类中心
        for idx in range(k):
            center_new[idx] = np.mean(data[res == idx], axis=0)
        # 如果两次训练之间的聚类中心没有变化则停止
        if np.sum(np.abs(centers - center_new)) < 1e-10:
            break
        centers = center_new

    return centers


# 距离函数列表
list_dist_fun = [Euclidean_Distance, Manhattan_Distance, Cosine_Similarity]
# 距离函数名字
list_dist_fun_name = ["欧氏距离", "曼哈顿距离", "余弦相似度"]
# K值列表
list_k = [2, 3, 4, 5, 6, 10]

# 测试函数
if __name__ == "__main__":
    # 遍历data文件夹下所有数据集
    for file in os.listdir(directory):
        # 读取当前数据集
        data = np.loadtxt(directory + file)
        # 记录最佳的参数：当前值，距离函数，k值
        best_s_s = [0, 0, 0]
        best_c_h = [0, 0, 0]
        for dist_fun, fun_name in zip(list_dist_fun, list_dist_fun_name):
            fig = plt.figure(figsize=(17, 8))
            # 计算子图列数
            num_col = (len(list_k) + 1) >> 1

            for idx, k in enumerate(list_k):
                # 计算当前参数下的聚类中心
                centers = kmenas_train(data, dist_fun, k)
                labels = dist_fun(centers, data)
                # 创建子图
                ax = fig.add_subplot(2, num_col, idx + 1)
                ax.set_title(f"k = {k}")
                """
                # 计算轮廓系数
                 s_s = silhouette_score(data, labels)
                if s_s > best_s_s[0]:
                    best_s_s = [s_s, fun_name, k]

                c_h = calinski_harabasz_score(data, labels)
                if c_h > best_c_h[0]:
                    best_c_h = [c_h, fun_name, k
                """
                # 将每一类用不同的颜色标记
                for i in range(k):
                    ax.scatter(data[labels == i, 0], data[labels == i, 1])

            plt.suptitle(f"数据集{file}使用{fun_name}聚类")
            # 调整布局以防止重叠
            plt.tight_layout()
        """
        print(f"文件：{file}")
        print(f"使用轮廓系数评价的最佳参数：{best_s_s}")
        print(f"使用CH指数评价的最佳参数：{best_c_h}\n")
        """
        plt.show()
