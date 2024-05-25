# 导入轮廓系数和CH指数计算函数
from sklearn.metrics import silhouette_score, calinski_harabasz_score
import numpy as np
from matplotlib import rcParams
from sklearn.mixture import GaussianMixture
import matplotlib.pyplot as plt

# 导入我之前对聚类算法的实现
import kmeans
import dbscan

# 设置中文字体
rcParams["font.family"] = "SimHei"
# 解决负号问题
rcParams["axes.unicode_minus"] = False
# 定义数据集目录
directory = "data/"


def plot_and_compare(file_name, k, epsilon, minpts, num_class):
    print(f"数据集{file_name}：\n")
    fig = plt.figure(figsize=(17, 9))
    data = np.loadtxt(directory + file_name)
    # 使用K-Means聚类
    # 曼哈顿距离
    dist_fun = kmeans.Manhattan_Distance
    centers = kmeans.kmenas_train(data, dist_fun, k)
    labels_k_means = dist_fun(centers, data)
    # 计算轮廓指数
    s_s = silhouette_score(data, labels_k_means)
    # 计算CH指数
    c_h = calinski_harabasz_score(data, labels_k_means)
    # 创建子图
    ax = fig.add_subplot(2, 2, 1)
    ax.scatter(data[:, 0], data[:, 1], c=labels_k_means)
    ax.set_title(f"使用K-Means（曼哈顿距离）聚类，轮廓系数：{s_s:.4},CH指数：{c_h:.3}")

    # 余弦相似度
    dist_fun = kmeans.Cosine_Similarity
    centers = kmeans.kmenas_train(data, dist_fun, k)
    labels_k_means = dist_fun(centers, data)
    # 计算轮廓指数
    s_s = silhouette_score(data, labels_k_means)
    # 计算CH指数
    c_h = calinski_harabasz_score(data, labels_k_means)
    # 创建子图
    ax = fig.add_subplot(2, 2, 2)
    ax.scatter(data[:, 0], data[:, 1], c=labels_k_means)
    ax.set_title(f"使用K-Means（余弦相似度）聚类，轮廓系数：{s_s:.4},CH指数：{c_h:.3}")

    # 使用DBSCAN聚类
    data_as_tuples = [tuple(point) for point in data]
    C, k = dbscan.dbscan(data_as_tuples, epsilon, minpts)
    labels_dbscan = []
    data_dbscan = []
    # 从类别中取出标签
    for idx, c in enumerate(C):
        for x in c:
            a, b = x
            labels_dbscan.append(idx)
            data_dbscan.append([a, b])

    data_dbscan = np.array(data_dbscan)
    labels_dbscan = np.array(labels_dbscan)
    # 计算轮廓指数
    s_s = silhouette_score(data_dbscan, labels_dbscan)
    # 计算CH指数
    c_h = calinski_harabasz_score(data_dbscan, labels_dbscan)
    # 创建子图
    ax = fig.add_subplot(2, 2, 3)
    ax.scatter(data_dbscan[:, 0], data_dbscan[:, 1], c=labels_dbscan)
    ax.set_title(f"使用DBSCAN聚类，轮廓系数：{s_s:.4},CH指数：{c_h:.3}")

    # 使用GMM聚类
    gmm = GaussianMixture(n_components=num_class)
    gmm.fit(data)
    # 输出每个样本的所属聚类
    labels_gmm = gmm.predict(data)

    # 计算轮廓指数
    s_s = silhouette_score(data, labels_gmm)
    # 计算CH指数
    c_h = calinski_harabasz_score(data, labels_gmm)
    # 创建子图
    ax = fig.add_subplot(2, 2, 4)
    ax.scatter(data[:, 0], data[:, 1], c=labels_gmm)
    ax.set_title(f"使用GMM聚类，轮廓系数：{s_s:.4},CH指数：{c_h:.3}")

    plt.suptitle(f"数据集{file_name}四种聚类方案之间对比")
    print(f"数据集{file_name}四种聚类方案之间对比")
    plt.tight_layout()
    plt.show()


plot_and_compare("aniso.txt", 3, 0.1, 10, 3)
plot_and_compare("blobs.txt", 3, 0.2, 10, 3)
plot_and_compare("no_structure.txt", 1, 0.01, 10, 1)
plot_and_compare("noisy_circle.txt", 2, 0.01, 3, 2)
plot_and_compare("noisy_moons.txt", 2, 0.01, 5, 2)
plot_and_compare("varied.txt", 3, 0.5, 8, 3)
