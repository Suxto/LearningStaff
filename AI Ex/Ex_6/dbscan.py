import numpy as np
import matplotlib.pyplot as plt
import queue
from matplotlib import rcParams

# 设置中文字体
rcParams["font.family"] = "SimHei"
# 解决负号问题
rcParams["axes.unicode_minus"] = False
# 定义数据集目录
directory = "data/"


# 计算每个点周围的密度可达点的集合
def N(epsilon, data, x):
    data = np.array(data)
    return [tuple(point) for point in data[np.sum((data - x) ** 2, axis=1) < epsilon]]


# 聚类函数，返回所有类别
def dbscan(data, epsilon, minpts):
    # 核心对象集合
    omega = queue.Queue()
    # 初始化类别数和未访问样本集合
    k, L = 0, set(data)
    for x in L:
        # 将密度直达对象个数大于阈值的对象加入核心对象集合
        if len(N(epsilon, data, x)) >= minpts:
            omega.put(x)

    # 储存每个聚类的集合
    C = []
    # print(L)
    while not omega.empty():
        L_old = L.copy()
        # 选择最后一个核心对象并去除这个对象
        o = omega.get()
        if o in L:
            L.remove(o)
        # 当前点被访问过的话跳过这个核心对象
        else:
            continue
        q = queue.Queue()
        q.put(o)
        while not q.empty():
            now = q.get()
            # 计算当前点密度可达的集合
            reachable = set(N(epsilon, data, now))
            if len(reachable) >= minpts:
                delta = reachable & L
                # 将密度可达的集合加入队列
                for d in delta:
                    q.put(d)
                # 从未访问的数据集中去除这些可达的部分
                L = L - delta
        k += 1
        C.append(L_old - L)
        if len(L) == 0:
            break
    # print(k)
    return C, k


# 聚类并展示结果
def dbcan_and_show(file, epsilon, minpts):
    data = np.loadtxt(directory + file)
    # 中间用到了set，不转为元组无法哈希
    data_as_tuples = [tuple(point) for point in data]
    C, k = dbscan(data_as_tuples, epsilon, minpts)
    plt.figure()
    for c in C:
        x_values = [point[0] for point in c]
        y_values = [point[1] for point in c]
        plt.scatter(x_values, y_values)
    plt.title(f"数据集{file}使用DBSCAN聚类分为{k}类")
    print(f"数据集{file}使用DBSCAN聚类分为{k}类")
    # plt.figure()
    # plt.scatter(data[:, 0], data[:, 1])


# 测试函数
if __name__ == "__main__":
    # 对每个数据集进行聚类
    dbcan_and_show("aniso.txt", 0.1, 10)
    dbcan_and_show("blobs.txt", 0.2, 10)
    dbcan_and_show("no_structure.txt", 0.01, 10)
    dbcan_and_show("noisy_circle.txt", 0.01, 10)
    dbcan_and_show("noisy_moons.txt", 0.01, 10)
    dbcan_and_show("varied.txt", 0.45, 8)

    plt.tight_layout()
    plt.show()
