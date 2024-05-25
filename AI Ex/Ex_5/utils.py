import csv
import numpy as np
from tqdm import tqdm
from tabulate import tabulate


def load_data(path, num_attr=4, tag_col=4):
    print("loading data...")
    x, y = [], []
    with open(path, "r") as file:
        reader = csv.reader(file)
        title = next(reader)
        for row in tqdm(reader):
            x.append(np.array(row[:num_attr], dtype=float))
            y.append(row[tag_col])
    x = np.array(x)
    y = np.array(y)
    return x, y, title[:num_attr]


# 展示结果（混淆矩阵，查准率，查全率和F1_Score)
def display(cm, pc, rc, f1, tags):
    str = "\t" * (len(tags) // 2 - 1)
    print(f"{str}混淆矩阵")
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
