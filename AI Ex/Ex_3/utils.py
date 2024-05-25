import pandas as pd
import numpy as np


# 用于从文件导入数据
def load_data(file_loc):
    data = pd.read_excel(file_loc, sheet_name="Data", header=0)
    col_name = data.columns.to_list()[2:]
    col_name[1] = "N"
    # print(col_name)

    data_set = []
    # 按行读取文件
    for _, row in data.iterrows():
        data_set.append(row.to_numpy()[1:])

    # print(data_set)
    data_set = np.array(data_set)
    X = data_set[:, 1:]  # 特征值
    Y = data_set[:, 0]  # 标签
    tags = np.unique(Y)
    # 返回值是 特征值列表，标签列表，属性列名，标签种类列表
    return X, Y, col_name, tags


# 用于将数据集分为训练集和测试集
def split(X, Y, test_size, seed):
    # 设定给定的种子，保证每次随机的结果是相同的
    np.random.seed(seed)
    # 随机生成索引
    indices = np.random.permutation(len(X))
    test_set_size = int(len(X) * test_size)

    test_indices = indices[:test_set_size]
    train_indices = indices[test_set_size:]

    x_train = np.array([X[i] for i in train_indices])
    x_test = np.array([X[i] for i in test_indices])
    y_train = np.array([Y[i] for i in train_indices])
    y_test = np.array([Y[i] for i in test_indices])
    # 返回 训练集的特征值列表和标签，测试集的特征值列表和标签
    return x_train, y_train, x_test, y_test


# 测试函数
def test(tree, x, y, tags):
    tag_num = len(tags)
    tag_map = dict()
    for idx, tag in enumerate(tags):
        tag_map[tag] = idx
    # 多分类混淆矩阵，行为预测值，列为真实值
    confusion_matrix = np.zeros((tag_num, tag_num), dtype=int)
    for now, tag in zip(x, y):
        predict_tag = tree.predict([now])

        if len(np.shape(predict_tag)) > 0:
            predict_tag = predict_tag[0]

        confusion_matrix[tag_map[predict_tag], tag_map[tag]] += 1
    # 查准率
    precision = confusion_matrix.diagonal() / np.sum(confusion_matrix, axis=1)
    # 查全率
    recall = confusion_matrix.diagonal() / np.sum(confusion_matrix, axis=0)
    # F1
    f1 = 2 * np.multiply(precision, recall) / (precision + recall)
    return confusion_matrix, precision, recall, f1


# 展示结果（混淆矩阵，查准率，查全率和F1_Score)
def display(cm, pc, rc, f1, tags):
    print("\t\t混淆矩阵")
    print("\t\t\t真实值")
    str = "  \t"
    for x in tags:
        str += f"\t{x}"
    print(str)
    s_idx = 0
    str = "预测值"
    for idx, tag in enumerate(tags):
        now = str[s_idx] if idx < len(str) else "  "
        s_idx += 1
        now += f"\t{tag}"
        for i in cm[idx]:
            now += f"\t{i}"
        print(now)
    print()
    print("类型\t查准率\t查全率\tF1_Score")
    for idx, tag in enumerate(tags):
        print(f"{tag}\t{pc[idx]:.4}\t{rc[idx]:.4}\t{f1[idx]:.4}")
