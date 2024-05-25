import utils

# import numpy as np
from sklearn.ensemble import AdaBoostClassifier
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.metrics import accuracy_score
from sklearn.tree import DecisionTreeClassifier
import matplotlib.pyplot as plt
import seaborn as sns
from matplotlib import rcParams

# 设置中文字体
rcParams["font.family"] = "SimHei"
# 解决负号问题
rcParams["axes.unicode_minus"] = False

X, y, title = utils.load_data("data/iris.csv")
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)


algorithms = ["SAMME", "SAMME.R"]
for algorithm in algorithms:
    # 基学习器（深度为2的决策树）
    base_estimator = DecisionTreeClassifier(max_depth=2)

    # 建立AdaBoost分类器
    clf = AdaBoostClassifier(
        estimator=base_estimator, random_state=42, algorithm=algorithm
    )
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
    print("Accuracy:", accuracy)
    # 输出最佳模型的参数和性能指标
    print("Best Parameters:", grid_search.best_params_)
    print("Best Score:", grid_search.best_score_)

    # 绘制热力图
    param_scores = grid_search.cv_results_["mean_test_score"].reshape(
        len(param_grid["learning_rate"]), len(param_grid["n_estimators"])
    )

    # param_grid = np.round(np.array(param_grid), 2)

    # print(grid_search.cv_results_["mean_test_score"])
    fig, ax = plt.subplots(figsize=(11, 6))

    sns.heatmap(
        param_scores,
        annot=True,
        cmap="YlOrRd",
        vmin=0,
        vmax=1,
        yticklabels=param_grid["learning_rate"],
        xticklabels=param_grid["n_estimators"],
    )
    ax.set_title("AdaBoost 超参数热力图")
    ax.set_xlabel("基分类器个数")
    ax.set_ylabel("学习率")
    plt.tight_layout()
    plt.show()
