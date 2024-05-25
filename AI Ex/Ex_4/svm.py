from utils import load_data, display, tabulate, gen_data, np
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
from sklearn.metrics import accuracy_score
import matplotlib.pyplot as plt
from tqdm import tqdm
from time import time

# svm使用的核函数
svm_kernal = "rbf"  # "sigmoid","rbf"

# 加载数据集
X, Y, title = load_data("data\iris.csv")
tags = np.unique(Y)
# 将数据集划分为训练集和测试集
X_train, X_test, y_train, y_test = train_test_split(
    X, Y, test_size=0.3, random_state=42
)

# 定义不同的超参数
C_values = [0.001, 0.01, 0.1, 1, 5, 10, 100, 10000]
gamma_values = [0.001, 0.01, 0.1, 1, 5, 10, 100, 10000]

# 存储结果的字典
results = {}
times = []
# 遍历超参数组合
for c in C_values:
    for gamma in gamma_values:
        begin = time()
        # 初始化SVM分类器
        svm = SVC(kernel=svm_kernal, C=c, gamma=gamma)
        # 训练模型
        svm.fit(X_train, y_train)
        # 计算当前参数的模型在测试集上的准确率
        score = accuracy_score(y_test, svm.predict(X_test))
        # 存储结果
        results[(c, gamma)] = score
        times.append(time() - begin)

times = np.array(times)
print(f"平均训练用时{times.mean()}s")

# 打印结果
print("SVM分类器不同超参数的性能:")
headers = ["C", "gamma", "准确率"]
data = []
for params, score in results.items():
    data.append((params[0], params[1], score))
print(tabulate(data, headers=headers, floatfmt=".2f"))

# 取出最优参数
best_params = max(results, key=results.get)
best_svm = SVC(kernel=svm_kernal, C=best_params[0], gamma=best_params[1])
# 训练模型
best_svm.fit(X_train, y_train)
test_score = best_svm.score(X_test, y_test)
print("\n最佳模型在测试集上的准确率: {:.2f}\n".format(test_score))

# 将结果转换为二维数组以方便画图
scores = np.array(list(results.values())).reshape(len(C_values), len(gamma_values))
# 使用热力图展示结果
plt.imshow(scores, interpolation="nearest", cmap=plt.cm.tab10)
plt.title(f"SVM with Kernal {svm_kernal}")
plt.xlabel("gamma")
plt.ylabel("C")
plt.colorbar()
# 设置x轴上的数字
plt.xticks(np.arange(len(gamma_values)), gamma_values)
# 设置y轴上的数字
plt.yticks(np.arange(len(C_values)), C_values)


def conv(tag):
    return 0 if tag == tags[0] else 1


print(f"最佳模型的测试结果(C = {best_params[0]}, gamma = {best_params[1]}):")
time_begin = time()
# 对测试集进行预测
predicted_y = best_svm.predict(X_test)
# 混淆矩阵：纵轴为预测值，横轴为实际值
cm = np.zeros((2, 2))
for cur_tag, tag in zip(predicted_y, y_test):
    # 将预测标签从字符串转为数字
    cur_tag = conv(cur_tag)
    tag = conv(tag)
    cm[cur_tag][tag] += 1

print(f"测试用时{time()-time_begin}s")

# 查准率
precision = cm.diagonal() / np.sum(cm, axis=1)
# 查全率
recall = cm.diagonal() / np.sum(cm, axis=0)
# F1
f1 = 2 * np.multiply(precision, recall) / (precision + recall)
# 展示结果
display(cm, precision, recall, f1, tags)


# 取出svm的参数


def visualize_linear_model(svm, X, y):
    w, b = svm.coef_[0], svm.intercept_[0]
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
    sample0 = y == tags[0]
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


def visualize_svm(svm, X, y):
    fig = plt.figure()
    ax = fig.add_subplot(111, projection="3d")
    # 分离样本
    sample0 = y == tags[0]
    X_0, X_1 = X[sample0], X[~sample0]
    ax.scatter(X_0[:, 0], X_0[:, 1], X_0[:, 2], label=tags[0])
    ax.scatter(X_1[:, 0], X_1[:, 1], X_1[:, 2], label=tags[1])
    # 确定数据范围
    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    z_min, z_max = X[:, 2].min() - 1, X[:, 2].max() + 1
    # 在数据范围内均匀的生成点
    xx, yy, zz = np.meshgrid(
        np.linspace(x_min, x_max, 30),
        np.linspace(y_min, y_max, 30),
        np.linspace(z_min, z_max, 30),
    )
    points = np.c_[xx.ravel(), yy.ravel(), zz.ravel()]
    # 对这些点的类别进行预测
    Z = svm.predict(points)
    # 找出两类样本的下标
    C0 = Z == tags[0]
    C1 = ~C0
    # 分离两类样本
    dot0 = points[C0]
    dot1 = points[C1]
    # print(dot0.shape, dot1.shape)

    # 用于记录边节点的数组
    edge_dots0 = []
    edge_dots1 = []
    edge_dots = []
    for a in tqdm(dot0):
        tmp = np.sum(a - dot1, axis=1)
        idx = tmp == 0
        # 记录边界点
        if np.sum(tmp == 0) > 0:
            edge_dots0.append(a)
            for b in dot1[idx]:
                edge_dots1.append(b)
                edge_dots.append((a + b) / 2)

    def plot_surface(ax, dots, idx=0):
        dots = np.array(dots)
        print(f"ploting {dots.shape[0]} points")
        XX = dots[:, 0]
        YY = dots[:, 1]
        ZZ = dots[:, 2]
        ax.plot_trisurf(XX, YY, ZZ, alpha=0.1, label=f"divider_{idx}")

    # 分别画出样本边界和各自的支持向量
    plot_surface(ax, edge_dots0, 0)
    plot_surface(ax, edge_dots1, 1)
    plot_surface(ax, edge_dots, 2)
    ax.legend()
    # 设置坐标轴标签
    ax.set_xlabel(title[0])
    ax.set_ylabel(title[1])
    ax.set_zlabel(title[2])
    plt.show()


def benchmark(num):
    x = gen_data(X, num)
    time_begin = time()
    res = best_svm.predict(x)
    print(f"预测{num}个样本耗时{(time()-time_begin)*1000:.5}ms")
    print(res)


visualize_svm(best_svm, X_train, y_train)
benchmark(10000)
