import numpy as np
from utils import load_data, split, test, display

X, Y, col_name, tags = load_data("data/cork_stoppers.xlsx")
x_train, y_train, x_test, y_test = split(X, Y, 0.3, 42)


class Node:
    """
    决策树的节点

    属性:
        feature (int): 该节点所选择的特征的索引
        threshold (float):  特征划分的阈值
        left (Node): 左子树引用
        right (Node): 右子树引用
        type (int or None): 如果是叶子节点就是最终的类别, 不是则是None
    """

    def __init__(self, feature=None, threshold=None, left=None, right=None, type=None):
        self.feature = feature
        self.threshold = threshold
        self.left = left
        self.right = right
        self.type = type

    def predict(self, attrs):
        # 属性值可能是一个列表
        if len(np.shape(attrs)) > 1:
            attrs = attrs[0]
        # 到了叶子节点，返回叶子节点的值
        if self.type is not None:
            return self.type
        # 根据节点中储存的阈值决定遍历左或右子树
        if attrs[self.feature] < self.threshold:
            return self.left.predict(attrs)
        else:
            return self.right.predict(attrs)


# 计算信息熵
def entropy(y):
    # 数出每种标签的个数
    _, counts = np.unique(y, return_counts=True)
    # 计算占比
    prob = counts / len(y)
    # 得到信息熵
    return -np.sum(prob * np.log2(prob))


# 计算信息增益率
def information_gain_ratio(x, y, feature, threshold, pre_entropy):
    # 根据当前选择的中值划分数据集
    left_indices = x[:, feature] < threshold
    right_indices = ~left_indices

    # 计算比中值小部分的熵和占比
    left_entropy = entropy(y[left_indices])
    left_weight = np.sum(left_indices) / len(y)

    # 计算比中值大部分的熵和占比
    right_entropy = entropy(y[right_indices])
    right_weight = np.sum(right_indices) / len(y)

    # 计算信息增益
    information_gain = pre_entropy - (
        left_weight * left_entropy + right_weight * right_entropy
    )
    # 如果有一边的比例是1（当前选择的属性值均相等）需要特判以防止计算 log(0)
    left = left_weight if left_weight > 0 else 1
    right = right_weight if right_weight > 0 else 1
    # 计算并返回信息增益率
    iv = -np.sum(left * np.log2(left) + right * np.log2(right))
    if iv == 0:
        return -1
    return information_gain / iv


def build(x, y):
    # 如果当前数据集只有一个标签值，选择当前节点为叶子节点
    if len(np.unique(y)) == 1:
        return Node(type=y[0])

    best_gain_ratio = 0
    best_feature_idx = None
    best_threshold = None
    # 计算划分前的信息熵
    pre_entropy = entropy(y)

    # 对每个属性尝试划分
    for feature_idx in range(len(col_name)):
        # 取出当前计算的属性的列，保存为新数组
        cur_attr_list = x[:, feature_idx].copy()
        cur_attr_list.sort()
        thresholds = []
        # 在对数组排序之后存下两两之间的均值
        for pre, now in zip(cur_attr_list, cur_attr_list[1:]):
            thresholds.append((now + pre) / 2)

        thresholds = np.array(thresholds)
        # 对均值进行去重，简化后面的计算
        thresholds = np.unique(thresholds)
        for threshold in thresholds:
            # 对每个中值计算信息增益率
            gain_ratio = information_gain_ratio(
                x, y, feature_idx, threshold, pre_entropy
            )
            # 保存最好的信息增益率
            if gain_ratio >= best_gain_ratio:
                best_gain_ratio = gain_ratio
                best_feature_idx = feature_idx
                best_threshold = threshold
    # 根据阈值筛选出左右子树的数据集
    left_indices = x[:, best_feature_idx] < best_threshold
    right_indices = ~left_indices
    # 如果阈值左边有数据的话递归建立左子树
    if np.sum(left_indices) > 0:
        left_subtree = build(x[left_indices], y[left_indices])
    else:
        left_subtree = None
    # 如果阈值右边有数据的话递归建立右子树
    if np.sum(right_indices) > 0:
        right_subtree = build(x[right_indices], y[right_indices])
    else:
        right_subtree = None
    # 左右子树建立完成之后将引用保存在当前节点
    return Node(
        feature=best_feature_idx,
        threshold=best_threshold,
        left=left_subtree,
        right=right_subtree,
    )


tree = build(x_train, y_train)
cm, pc, rc, f1 = test(tree, x_test, y_test, tags)
display(cm, pc, rc, f1, tags)
