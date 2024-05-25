from utils import (
    load_data,
    standardize,
    sigmoid,
    dsigmoid,
    display,
    gen_data,
    np,
    standardize_list,
)
from random import random
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split  # , cross_val_score
from tqdm import tqdm
from time import time

X, Y, title = load_data("data\iris.csv")
print(title)
tags = np.unique(Y)
# 学习率
alpha = 0.01
# 训练次数
epoch = 2000
# 样本个数和参数个数
num_all, num_para = X.shape
# print(X.shape)
# y = sigmoid(a0 + a1 * x1 + a2 * x2 + x3 * a3 + x4 * a4...)

# 存储每个特征的标准差，平均值和参数
std, mean, a = [], [], [random()]
# 对数据集进行标准化
for i in range(num_para):
    s, m, X[:, i] = standardize(X[:, i])
    std.append(s)
    mean.append(m)
    a.append(random())

# 将标签转化为 0 和 1 进行二分类
Y_conv = []
for tag in Y:
    if tag == tags[0]:
        Y_conv.append(0)
    else:
        Y_conv.append(1)
Y = np.array(Y_conv, dtype=float)

# 将数据集划分为训练集和测试集
X_train, X_test, y_train, y_test = train_test_split(
    X, Y, test_size=0.3, random_state=42
)

num = X_train.shape[0]
print("training...")
time_begin = time()
for _ in tqdm(range(epoch)):
    # for _ in range(epoch):
    sum = [0.0] * len(a)
    for x, tag in zip(X_train, y_train):
        # 计算预测标签
        inner = np.sum(a[1:] * x) + a[0]
        cur_tag = sigmoid(inner)
        # 计算梯度
        diff = cur_tag - tag
        sum[0] += diff * dsigmoid(inner, 1)
        for i in range(1, len(a)):
            sum[i] += diff * dsigmoid(inner, x[i - 1])

    for i, cur_sum in enumerate(sum):
        a[i] -= alpha * (cur_sum / num)

print(f"训练用时{time()-time_begin}s")

# 混淆矩阵：纵轴为预测值，横轴为实际值
cm = np.zeros((2, 2))
for x, tag in zip(X_test, y_test):
    # 计算预测标签
    inner = np.sum(a[1:] * x) + a[0]
    cur_tag = sigmoid(inner)
    cur_tag = round(cur_tag)
    tag = round(tag)
    cm[cur_tag][tag] += 1

# 查准率
precision = cm.diagonal() / np.sum(cm, axis=1)
# 查全率
recall = cm.diagonal() / np.sum(cm, axis=0)
# F1
f1 = 2 * np.multiply(precision, recall) / (precision + recall)
# 展示结果
display(cm, precision, recall, f1, tags)


def visualize_model(X, y):
    # 取出模型权重
    w, b = a[1:], a[0]
    # 创建网格点以绘制决策边界
    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    xx, yy = np.meshgrid(np.arange(x_min, x_max, 0.1), np.arange(y_min, y_max, 0.1))

    # 计算决策平面
    decision_plane = -(w[0] * xx + w[1] * yy + b) / w[2]

    # 创建3D图形对象
    fig = plt.figure()
    ax = fig.add_subplot(111, projection="3d")

    # 绘制决策平面
    ax.plot_surface(xx, yy, decision_plane, alpha=0.5)
    # 绘制两种类型的散点图

    # 分离样本
    sample0 = y == 0
    X_0, X_1 = X[sample0], X[~sample0]
    ax.scatter(X_0[:, 0], X_0[:, 1], X_0[:, 2], label=tags[0])
    ax.scatter(X_1[:, 0], X_1[:, 1], X_1[:, 2], label=tags[1])
    ax.legend()
    # 设置坐标轴标签
    ax.set_xlabel(title[0])
    ax.set_ylabel(title[1])
    ax.set_zlabel(title[2])
    # 显示图形
    plt.show()


def benchmark(num):
    np.random.seed(42)
    tag = np.array(tags)
    x = gen_data(X, num)
    x = standardize_list(x, mean, std)
    time_begin = time()
    # y=np.random.choice([0,1])
    res = tag[np.round(sigmoid(np.sum(a[1:] * x, axis=1) + a[0])).astype(int)]
    print(f"预测{num}个样本耗时{(time()-time_begin)*1000:.5}ms")
    print(res)


visualize_model(X_train, y_train)
benchmark(10000)
