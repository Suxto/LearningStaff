from sklearn.tree import DecisionTreeClassifier
# 我写的工具类
from utils import load_data, split, test, display

X, Y, col_name, tags = load_data("data/cork_stoppers.xlsx")
x_train, y_train, x_test, y_test = split(X, Y, 0.3, 42)
# 创建CART模型
tree = DecisionTreeClassifier(criterion="gini")
# 训练模型
tree.fit(x_train, y_train)

cm, pc, rc, f1 = test(tree, x_test, y_test, tags)
display(cm, pc, rc, f1, tags)
