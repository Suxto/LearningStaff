import csv
import numpy as np
from tabulate import tabulate


def load_data(path):
    # 使用的特征数
    ATTR_NUM = 3
    # 标签的列
    TAG_COL = 4
    x, y = [], []
    with open(path, "r") as file:
        reader = csv.reader(file)
        title = next(reader)
        for row in reader:
            x.append(np.array(row[:ATTR_NUM], dtype=float))
            y.append(row[TAG_COL])
    x = np.array(x)
    y = np.array(y)
    return x, y, title[:ATTR_NUM]


def standardize(list):
    mean = np.mean(list)
    std = np.std(list)
    return std, mean, (list - mean) / std


def standardize_list(list, mean, std):
    for i in range(list.shape[1]):
        list[:, i] = standardize_with_paras(list[:, i], mean[i], std[i])
    return list


def standardize_with_paras(list, mean, std):
    return (list - mean) / std


def sigmoid(z):
    return 1 / (1 + np.exp(-z))


def dsigmoid(z, x):
    return x * sigmoid(z) * (1 - x * sigmoid(z))


# 展示结果（混淆矩阵，查准率，查全率和F1_Score)
def display(cm, pc, rc, f1, tags):
    print("\t\t混淆矩阵")
    headers = ["预测\实际"]
    data = []
    for x in tags:
        headers.append(x)
    for idx, tag in enumerate(tags):
        row = [tag] + cm[idx].tolist()
        data.append(row)
    print(tabulate(data, headers=headers))
    print("\n\t\t性能数据")
    headers = ["类型", "查准率", "查全率", "F1_Score"]
    data = []
    for idx, tag in enumerate(tags):
        row = [tag, pc[idx], rc[idx], f1[idx]]
        data.append(row)
    print(tabulate(data, headers=headers))


# 随机生成测试数据
def gen_data(X, num):
    np.random.seed(42)
    # X, _, _ = load_data("data/iris.csv")
    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    z_min, z_max = X[:, 2].min() - 1, X[:, 2].max() + 1
    X_new = np.zeros((num, 3))
    X_new[:, 0] = np.random.uniform(x_min, x_max + 0.1, (num,))
    X_new[:, 1] = np.random.uniform(y_min, y_max + 0.1, (num,))
    X_new[:, 2] = np.random.uniform(z_min, z_max + 0.1, (num,))
    return X_new
