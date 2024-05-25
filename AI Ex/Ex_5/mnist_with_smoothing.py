import numpy as np
import matplotlib.pyplot as plt
import utils
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score
from sklearn.decomposition import PCA

from matplotlib import rcParams

# 设置中文字体
rcParams["font.family"] = "SimHei"

# 设置降维的维度，0为不降维
pca = 75

# 加载数据
X, y, _ = utils.load_data("data/mnist.csv", 784, 784)
y = np.array(y, dtype=int)
# 划分数据集为训练集和测试集
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.3, random_state=42
)

# 如果降维则对数据集进行操作
if pca != 0:
    pca = PCA(n_components=pca)
    # 得到投影矩阵
    pca.fit(X_train)
    W = pca.components_.T
    # 通过投影矩阵将当前训练集和测试集降维
    X_train = X_train @ W
    X_test = X_test @ W


smoothings = np.linspace(1e-7, 5e-3, 40)
accuracys = []
for i in utils.tqdm(smoothings):
    # 初始化朴素贝叶斯分类器
    naive_bayesian = GaussianNB(var_smoothing=i)
    # 训练模型
    naive_bayesian.fit(X_train, y_train)
    # 预测测试集
    y_pred = naive_bayesian.predict(X_test)
    # 计算测试集准确率
    accuracy = accuracy_score(y_test, y_pred)
    accuracys.append(accuracy)
    # print(f"alpha为{i}的时候，测试集上的准确度{accuracy}")

idx = np.argmax(accuracys)
print(f"当aplha={smoothings[idx]:.3}时，最佳准确度为{accuracys[idx]:.3}")

plt.figure(figsize=(15, 6))
# 画出折线图
plt.plot(smoothings, accuracys, marker="o", linestyle="-")
# 在每个数据点上显示具体数字
for i, (x, y) in enumerate(zip(smoothings, accuracys)):
    plt.text(x, y, f"{y:.3}", fontsize=10, ha="right")

plt.title("平滑系数和准确率的关系")
plt.xlabel("alpha")
plt.ylabel("准确率")
plt.grid(True)
plt.show()
