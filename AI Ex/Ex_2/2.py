import random
import numpy as np
import matplotlib.pyplot as plt

num = 100
a = [random.random(), random.random(), random.random()]
alpha = 0.0015

list_x = np.zeros(num)
list_y = np.zeros(num)
for i in range(num):
    list_x[i] = random.random()
    list_y[i] = random.random() * 0.2 - 0.1 + list_x[i] * list_x[i] + list_x[i] + 1

# 排序
sorted_indices = sorted(range(len(list_x)), key=lambda k: list_x[k])
list_x = [list_x[i] for i in sorted_indices]
list_y = [list_y[i] for i in sorted_indices]

# y = a0 + a1 * x + a_2 * x * x

for _ in range(10000):
    sum = [0.0] * len(a)
    for x, y in zip(list_x, list_y):
        cur_y = a[0] + a[1] * x + a[2] * x * x
        sum[0] += cur_y - y
        sum[1] += (cur_y - y) * x
        sum[2] += (cur_y - y) * x * x
    for i, cur_sum in enumerate(sum):
        a[i] -= alpha * (cur_sum / num)

res_y = np.zeros(num)
for i, x in enumerate(list_x):
    res_y[i] = a[0] + a[1] * x + a[2] * x * x

plt.plot(list_x, res_y, "r")
plt.scatter(list_x, list_y)
plt.show()
