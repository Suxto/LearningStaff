import csv
import numpy as np


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
