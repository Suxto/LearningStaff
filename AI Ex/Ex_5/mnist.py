import numpy as np
import matplotlib.pyplot as plt
import utils
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score


from matplotlib import rcParams

# 设置中文字体
rcParams["font.family"] = "SimHei"

# 加载数据
X, y, _ = utils.load_data("data/mnist.csv", 784, 784)
y = np.array(y, dtype=int)
# 划分数据集为训练集和测试集
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.3, random_state=42
)

# 初始化朴素贝叶斯分类器
naive_bayesian = GaussianNB()

# 训练模型
naive_bayesian.fit(X_train, y_train)

# 预测测试集
y_pred = naive_bayesian.predict(X_test)

# 计算测试集准确率
accuracy = accuracy_score(y_test, y_pred)
print("总体在测试集上的准确度:", accuracy)

# 计算每个数字的准确率
tags = np.unique(y_test)
accuracys = []
for digit in tags:
    idx = y_test == digit
    accuracys.append(accuracy_score(y_test[idx], y_pred[idx]))

tag_num = len(tags)
# 多分类混淆矩阵，行为预测值，列为真实值
confusion_matrix = np.zeros((tag_num, tag_num), dtype=int)

# 记录每个分类结果的真实值和预测值
for real, pred in zip(y_test, y_pred):
    confusion_matrix[pred][real] += 1

# 查准率
precision = confusion_matrix.diagonal() / np.sum(confusion_matrix, axis=1)
# 查全率
recall = confusion_matrix.diagonal() / np.sum(confusion_matrix, axis=0)
# F1
f1 = 2 * np.multiply(precision, recall) / (precision + recall)
# 展示结果（混淆矩阵，查准率，查全率和F1_Score)
utils.display(confusion_matrix, precision, recall, f1, tags)

# 画出准确率图表
plt.figure(figsize=(12, 6))
bars = plt.bar(tags, accuracys)

# 在每个柱子上方添加准确率
for acc, bar in zip(accuracys, bars):
    h = bar.get_height() + 0.01
    x, y = bar.get_x(), bar.get_width()
    plt.text(x + y / 2, h, f"{acc:.3}", ha="center", va="bottom")

plt.xlabel("数字")
plt.ylabel("准确率")
plt.title("各个数字的准确率")
plt.xticks(tags)
# plt.grid(True)
plt.show()
