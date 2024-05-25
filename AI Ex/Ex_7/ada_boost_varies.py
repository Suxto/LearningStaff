import utils

import numpy as np
from sklearn.ensemble import AdaBoostClassifier
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.metrics import accuracy_score
from sklearn.tree import DecisionTreeClassifier
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.naive_bayes import GaussianNB
from sklearn.linear_model import LogisticRegression
from sklearn.svm import SVC
import os
from matplotlib import rcParams
from mlxtend.evaluate import bias_variance_decomp
# from sklearn.model_selection import cross_val_score


# 设置中文字体
rcParams["font.family"] = "SimHei"
# 解决负号问题
rcParams["axes.unicode_minus"] = False
os.environ["PYTHONWARNINGS"] = "ignore"


X, y, title = utils.load_data("data/iris.csv")
labels = np.unique(y)
label_to_int = {label: i for i, label in enumerate(labels)}
y = np.array([label_to_int[label] for label in y])

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)


# 基学习器数组
base_estimators = [
    DecisionTreeClassifier(max_depth=1),  # 决策桩
    DecisionTreeClassifier(max_depth=5),  # 深度为5的决策树
    LogisticRegression(),  # 逻辑回归
    SVC(probability=True),  # SVM
    GaussianNB(),  # 朴素贝叶斯
]
base_estimators_name = ["决策桩", "深度为5的决策树", "逻辑回归", "SVM", "朴素贝叶斯"]

# 作为基分类器的准确率
base_estimators_acc = []
# 直接分类的准确率
stand_alone_acc = []

# 预期损失，偏差和方差
list_expected_loss, list_bias, list_var = [], [], []

for base_estimator, name in zip(base_estimators, base_estimators_name):
    # 建立AdaBoost分类器
    clf = AdaBoostClassifier(estimator=base_estimator, random_state=42)
    # 参数字典
    param_grid = {
        "n_estimators": [10, 30, 50, 100, 150, 200],
        "learning_rate": [0.05, 0.1, 0.5, 1.0, 1.5],
    }
    # 构建网格搜索器
    grid_search = GridSearchCV(clf, param_grid, cv=5, scoring="accuracy", n_jobs=-1)
    grid_search.fit(X_train, y_train)

    # 使用最佳参数进行预测
    best_clf = grid_search.best_estimator_
    y_pred = best_clf.predict(X_test)
    accuracy = accuracy_score(y_test, y_pred)

    # 储存当前模型的准确率
    base_estimators_acc.append(accuracy)

    print("Accuracy:", accuracy)
    # 输出最佳模型的参数和性能指标
    print("Best Parameters:", grid_search.best_params_)
    print("Best Score:", grid_search.best_score_)

    # 绘制热力图
    param_scores = grid_search.cv_results_["mean_test_score"].reshape(
        len(param_grid["learning_rate"]), len(param_grid["n_estimators"])
    )

    # 计算预期损失，偏差和方差
    avg_expected_loss, avg_bias, avg_var = bias_variance_decomp(
        best_clf, X_train, y_train, X_test, y_test, random_seed=42, loss="mse"
    )
    list_expected_loss.append(avg_expected_loss)
    list_bias.append(avg_bias)
    list_var.append(avg_var)

    # print(grid_search.cv_results_["mean_test_score"])
    fig, ax = plt.subplots(figsize=(11, 6))

    sns.heatmap(
        param_scores,
        annot=True,
        cmap="YlOrRd",
        # vmin=0,
        # vmax=1,
        yticklabels=param_grid["learning_rate"],
        xticklabels=param_grid["n_estimators"],
    )
    ax.set_title(f"AdaBoost（{name}）超参数热力图")
    print(f"AdaBoost（{name}）超参数热力图")
    ax.set_xlabel("基分类器个数")
    ax.set_ylabel("学习率")
    plt.tight_layout()

    # 测试不集成直接分类
    base_estimator.fit(X_train, y_train)
    y_pred = base_estimator.predict(X_test)
    accuracy = accuracy_score(y_test, y_pred)
    stand_alone_acc.append(accuracy)

# plt.show()

# 绘制AdaBoost柱状图
fig, ax = plt.subplots(figsize=(8, 6))

# 绘制柱状图
bars = ax.bar(base_estimators_name, base_estimators_acc)

# 在柱子上标注数据
for bar, value in zip(bars, base_estimators_acc):
    height = bar.get_height()
    ax.annotate(
        f"{value:.3}",
        xy=(bar.get_x() + bar.get_width() / 2, height),
        xytext=(0, 3),  # 3 points vertical offset
        textcoords="offset points",
        ha="center",
        va="bottom",
    )

# 设置标题和轴标签
ax.set_title("不同基分类器在测试集使用AdaBoost(SAMME.R)的准确率")
ax.set_xlabel("基分类器")
ax.set_ylabel("在测试集上的准确率")

# 绘制使用单个分类器柱状图
fig, ax = plt.subplots(figsize=(8, 6))

# 绘制柱状图
bars = ax.bar(base_estimators_name, stand_alone_acc)

# 在柱子上标注数据
for bar, value in zip(bars, stand_alone_acc):
    height = bar.get_height()
    ax.annotate(
        f"{value:.3}",
        xy=(bar.get_x() + bar.get_width() / 2, height),
        xytext=(0, 3),  # 3 points vertical offset
        textcoords="offset points",
        ha="center",
        va="bottom",
    )

# 设置标题和轴标签
ax.set_title("不同分类器在测试集不集成的准确率")
ax.set_xlabel("分类器")
ax.set_ylabel("在测试集上的准确率")


# 创建图像和坐标轴
fig, ax = plt.subplots(figsize=(15, 8))

# 绘制三组柱状图
x = range(len(base_estimators_name))
width = 0.25
ax.bar(x, list_expected_loss, width, color="blue", label="期望损失")
ax.bar([i + width for i in x], list_bias, width, color="orange", label="偏差")
ax.bar([i + 2 * width for i in x], list_var, width, color="green", label="方差")

# 在柱子上标注数据
for i, (v1, v2, v3) in enumerate(zip(list_expected_loss, list_bias, list_var)):
    ax.text(i, v1 + 0.002, f"{v1:.2}", ha="center", va="bottom")
    ax.text(i + width, v2 + 0.002, f"{v2:.2}", ha="center", va="bottom")
    ax.text(i + 2 * width, v3 + 0.002, f"{v3:.2}", ha="center", va="bottom")

# 设置标题和坐标轴标签
ax.set_title("基分类器的期望损失、偏差和方差")
ax.set_xlabel("基分类器")
ax.set_xticks([i + width for i in x])
ax.set_xticklabels(base_estimators_name)
ax.legend()


# 显示图像
plt.show()
