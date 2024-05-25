import csv
import random
import numpy as np
import matplotlib.pyplot as plt
from tqdm import tqdm

mp = dict()
mp["setosa"] = []
mp["versicolor"] = []


def standardize(list):
    mean = np.mean(list)
    std = np.std(list)
    return std, mean, (list - mean) / std


# 采用前两个参数
with open("data/iris.csv", "r", newline="") as file:
    csvreader = csv.reader(file)
    next(csvreader)
    for row in csvreader:
        mp[row[5]].append(np.array(row[3:5], dtype=float))

list_s = np.array(mp["setosa"])
list_v = np.array(mp["versicolor"])


list_s_with_tag = np.concatenate((list_s, np.ones((list_s.shape[0], 1))), axis=1)

list_v_with_tag = np.concatenate((list_v, np.zeros((list_v.shape[0], 1))), axis=1)

list_with_tag = np.concatenate((list_s_with_tag, list_v_with_tag), axis=0)


s_len_list = list_s[:, 0]
s_width_list = list_s[:, 1]

v_len_list = list_v[:, 0]
v_width_list = list_v[:, 1]


std_x1, mean_x1, list_with_tag[:, 0] = standardize(list_with_tag[:, 0])
std_x2, mean_x2, list_with_tag[:, 1] = standardize(list_with_tag[:, 1])


def sigmoid(z):
    return 1 / (1 + np.exp(-z))


def dsigmoid(z, x):
    return x * sigmoid(z) * (1 - x * sigmoid(z))


# y = sigmoid(a0 + a1 * x1 + a2 * x2)

a = [random.random(), -random.random(), -random.random()]
num = list_with_tag.shape[0]

alpha = 0.01
for _ in tqdm(range(5000)):
    sum = [0.0] * len(a)
    for x1, x2, tag in list_with_tag:
        inner = a[0] + a[1] * x1 + a[2] * x2
        cur_tag = sigmoid(inner)
        diff = cur_tag - tag
        sum[0] += diff * dsigmoid(inner, 1)
        sum[1] += diff * dsigmoid(inner, x1)
        sum[2] += diff * dsigmoid(inner, x2)
    for i, cur_sum in enumerate(sum):
        a[i] -= alpha * (cur_sum / num)

print(a)
minn = np.min(list_with_tag[:, 0])
maxn = np.max(list_with_tag[:, 0])

list_x = np.linspace(minn, maxn, num)

list_y = (-(a[0] + a[1] * list_x) / a[2]) * std_x2 + mean_x2
list_x = list_x * std_x1 + mean_x1

plt.plot(list_x, list_y, color="g", label="Boundary")
plt.scatter(s_len_list, s_width_list, color="r", label="setosa")
plt.scatter(v_len_list, v_width_list, color="b", label="versicolor")
plt.legend()

k = -(a[1] * std_x2) / (a[2] * std_x1)
b = -a[0] * std_x2 / a[2] + mean_x2 - mean_x1 * a[1] * std_x2 / (a[2] * std_x1)
plt.title(f"y = {k}x " + ("+" if b > 0 else "-") + f" {abs(b)}")
plt.show()
