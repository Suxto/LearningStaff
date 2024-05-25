import csv
import random
import matplotlib.pyplot as plt
import numpy as np
from math import pow, log

list_x, list_y = [], []
with open("data/data_1.csv", "r", newline="") as file:
    data_as_list = []
    csv_reader = csv.reader(file)
    for row in csv_reader:
        data_as_list.append(row)
    list_x = [float(x) for x in data_as_list[0]]
    list_y = [float(y) for y in data_as_list[1]]

num = float(len(list_x))
# plt.scatter(x, y)
# plt.show()


def calc_diff(res):
    ans = 0
    for y, y_hat in zip(list_y, res):
        ans += (y - y_hat) ** 2
    return ans / (num * 2)


# 线性拟合
# 设函数为 y = a0 + a1 * x
def linear_fitting():
    a0 = random.random()
    a1 = random.random()
    alpha = 0.000001
    for _ in range(30000):
        sum_a0, sum_a1 = 0, 0
        for x, y in zip(iter(list_x), iter(list_y)):
            cur_y = a0 + a1 * x
            sum_a0 += cur_y - y
            sum_a1 += (cur_y - y) * x
        a0 -= alpha * (sum_a0 / num)
        a1 -= alpha * (sum_a1 / num)
        # print(a0, a1, sum_a0, sum_a1)
    print(a0, a1)
    res_y = []
    for x in list_x:
        res_y.append(a0 + a1 * x)

    diff = calc_diff(res_y)
    plt.text(11, 0.5, "loss=" + str(diff))
    plt.title(f"y = {a0} + {a1}x")
    plt.plot(list_x, res_y, "r")
    plt.scatter(list_x, list_y)
    plt.show()


def standardz(list):
    mean = np.mean(list)
    std = np.std(list)
    return std, mean, (list - mean) / std


# 多项式二次拟合
# 设函数多项式为 y = a0 + a1 * x + a2 * x * x
def quadratic_polynomial_fitting():
    # 转换为多元线性回归 y = a0 + a1 * x1 + a2 * x2
    std_x1, mean_x1, list_x1_std = standardz(np.array(list_x))
    std_x2, mean_x2, list_x2_std = standardz(np.array([x * x for x in list_x]))
    std_y, mean_y, list_y_std = standardz(np.array(list_y))

    a = [random.random(), random.random(), random.random()]
    alpha = 0.03
    for _ in range(10000):
        sum = [0, 0, 0]
        for x1, x2, y in zip(list_x1_std, list_x2_std, list_y_std):
            cur_y = a[0] + a[1] * x1 + a[2] * x2
            sum[0] += cur_y - y
            sum[1] += (cur_y - y) * x1
            sum[2] += (cur_y - y) * x2
        for i, cur_sum in enumerate(iter(sum)):
            a[i] -= alpha * (cur_sum / num)
    print(a)

    res_y = []
    for x in list_x:
        y_std = a[0] + a[1] * (x - mean_x1) / std_x1 + a[2] * (x * x - mean_x2) / std_x2
        res_y.append(y_std * std_y + mean_y)

    calc_x = 200
    y_std = a[1] + 2 * a[2] * (calc_x - mean_x1) / std_x1
    print(y_std * std_y + mean_y)

    plt.title(f"y = {a[0]} + {a[1]}x + {a[2]}x^2")
    diff = calc_diff(res_y)
    plt.text(11, 0.5, "loss=" + str(diff))
    plt.plot(list_x, res_y, "r")
    plt.scatter(list_x, list_y)
    plt.show()


# 多项式三次拟合
# 设函数多项式为 y = a0 + a1 * x + a2 * x * x + a3 * x * x * x
def cubic_polynomial_fitting():
    # 转换为多元线性回归 y = a0 + a1 * x1 + a2 * x2 + a3 * x3
    std_x1, mean_x1, list_x1_std = standardz(np.array(list_x))
    std_x2, mean_x2, list_x2_std = standardz(np.array([x * x for x in list_x]))
    std_x3, mean_x3, list_x3_std = standardz(np.array([x * x * x for x in list_x]))
    std_y, mean_y, list_y_std = standardz(np.array(list_y))

    a = [random.random(), random.random(), random.random(), random.random()]
    alpha = 0.03
    for _ in range(10000):
        sum = [0, 0, 0, 0]
        for x1, x2, x3, y in zip(list_x1_std, list_x2_std, list_x3_std, list_y_std):
            cur_y = a[0] + a[1] * x1 + a[2] * x2 + a[3] * x3
            sum[0] += cur_y - y
            sum[1] += (cur_y - y) * x1
            sum[2] += (cur_y - y) * x2
            sum[3] += (cur_y - y) * x3
        for i, cur_sum in enumerate(iter(sum)):
            a[i] -= alpha * (cur_sum / num)
    print(a)

    res_y = []
    for x in list_x:
        y_std = (
            a[0]
            + a[1] * (x - mean_x1) / std_x1
            + a[2] * (x * x - mean_x2) / std_x2
            + a[3] * (x * x * x - mean_x3) / std_x3
        )
        res_y.append(y_std * std_y + mean_y)

    plt.title(f"y = {a[0]} + {a[1]}x + {a[2]}x^2 + {a[3]}x^3")
    diff = calc_diff(res_y)
    plt.text(11, 0.5, "loss=" + str(diff))
    plt.plot(list_x, res_y, "r")
    plt.scatter(list_x, list_y)
    plt.show()


# 对数函数拟合
# 设函数为 y = a0 + a1 * log(x)
def logarithmic_function_fitting():
    def fun(a0, a1, x):
        return a0 + a1 * np.log(x)

    a = [random.random(), random.random()]
    alpha = 0.005

    for _ in range(40000):
        sum = [0] * 2
        for x, y in zip(list_x, list_y):
            cur_y = fun(a[0], a[1], x)
            sum[0] += cur_y - y
            sum[1] += (cur_y - y) * np.log(x)
        for i, cur_sum in enumerate(sum):
            a[i] -= alpha * cur_sum / num
    print(a)

    res_y = []
    for x in list_x:
        res_y.append(a[0] + a[1] * np.log(x))

    plt.title(f"y = {a[0]} + {a[1]}log(x)")
    diff = calc_diff(res_y)
    plt.text(11, 0.5, "loss=" + str(diff))
    plt.plot(list_x, res_y, "r")
    plt.scatter(list_x, list_y)
    plt.show()


# 幂函数拟合
# 设函数为 y = a0 + a1 * x^a2
def power_function_fitting():
    def fun(a, x):
        return a[0] + a[1] * pow(x, a[2])

    a = [random.random(), random.random(), random.random()]
    alpha = 0.00078
    for _ in range(13000):
        sum = [0] * 3
        for x, y in zip(list_x, list_y):
            cur_y = fun(a, x)
            sum[0] += cur_y - y
            sum[1] += (cur_y - y) * pow(x, a[2])
            sum[2] += (cur_y - y) * a[1] * pow(x, a[2]) * log(x)
        for i, cur_sum in enumerate(iter(sum)):
            a[i] -= alpha * (cur_sum / num)
    print(a)

    res_y = []
    for x in list_x:
        # y_std = fun(a, (x - mean_x) / std_x)
        # res_y.append(y_std * std_y + mean_y)
        res_y.append(fun(a, x))

    diff = calc_diff(res_y)
    plt.text(11, 0.5, "loss=" + str(diff))

    plt.title(f"y = {a[0]} + {a[1]}x^{a[2]}")
    plt.plot(list_x, res_y, "r")
    plt.scatter(list_x, list_y)
    plt.show()


# 由于参数的初值是随机生成的，可能需要运行几次才能得到最好的结果
# 线性
linear_fitting()
# 二次
quadratic_polynomial_fitting()
# 三次
cubic_polynomial_fitting()
# 对数
logarithmic_function_fitting()
# 幂
power_function_fitting()
