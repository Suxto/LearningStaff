import utils
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split


from matplotlib import rcParams

# 设置中文字体
rcParams["font.family"] = "SimHei"

# 加载数据
X, Y, name_attr = utils.load_data("data/iris.csv")

# 划分数据
X_train, X_test, y_train, y_test = train_test_split(
    X, Y, test_size=0.2, random_state=42
)

# 得到特征值名称和数目
name_attr = np.array(name_attr)
num_train_sample, num_attr = X_train.shape
# 得到标签
name_tags = np.unique(Y)
num_tag = len(name_tags)

# 根据标签划分数据集并计算每个种类下的平均值和方差
X_train_divided = [[] for _ in name_tags]
# 每个种类的频率
p = [0.0] * num_tag
# mean 和 var 第一个下标代表种类，第二个下标代表特征
mean = X_train_divided.copy()
var = X_train_divided.copy()

for idx, tag in enumerate(name_tags):
    # 得到当前种类的布尔索引
    mask = y_train == tag
    X_train_divided[idx] = X_train[mask]
    # 计算当前种类所有特征值的均值和方差
    mean[idx] = np.mean(X_train_divided[idx], axis=0)
    var[idx] = np.mean(X_train_divided[idx], axis=0)
    # 计算当前种类的频率
    p[idx] = len(X_train_divided[idx]) / num_train_sample


# 将掩码转换为布尔索引
def mask_conv(mask):
    idx = [False] * num_attr
    for i in range(num_attr):
        if mask & (1 << i) > 0:
            # 将当前位上为1的下标置1
            idx[i] = True
    idx.reverse()
    return idx


# 储存使用的特征值和准确率
arr_attrs = []
arr_acc = []

# 对每种特征组合进行测试
for mask in range(1, 1 << num_attr):
    idx = mask_conv(mask)
    # 选出需要的特征值
    x = X_test[:, idx]
    # 记录结果
    res = np.zeros((x.shape[0], num_tag))
    # 计算每一个种类的可能性
    for cur_type in range(num_tag):
        frac = 1 / (np.sqrt(2 * np.pi) * np.sqrt(var[cur_type][idx]))
        res[:, cur_type] = (
            np.prod(
                frac
                * np.exp(-((x - mean[cur_type][idx]) ** 2) / (2 * var[cur_type][idx])),
                axis=1,
            )
            * p[cur_type]
        )
    # 选择可能性最高的类型
    y_predict = [name_tags[i] for i in np.argmax(res, axis=1)]
    # 计算正确率
    accuracy = np.sum(y_predict == y_test) / y_test.shape[0]
    # 存储结果
    arr_attrs.append(name_attr[idx])
    arr_acc.append(accuracy)
    # print(f"{name_attr[idx]}, {accuracy}")

# 排序
sorted_indices = np.lexsort((arr_acc, [len(attr) for attr in arr_attrs]))
arr_attrs = [str(arr_attrs[idx]) for idx in sorted_indices]
arr_acc = np.array(arr_acc)[sorted_indices]

# 展示柱状图
plt.figure(figsize=(14, 7))
bars = plt.bar(
    np.arange(len(arr_acc)),
    arr_acc,
    tick_label=arr_attrs,
)

# 在每个柱子上方添加具体数字
for acc, bar in zip(arr_acc, bars):
    h = bar.get_height() + 0.01
    x, y = bar.get_x(), bar.get_width()
    plt.text(x + y / 2, h, f"{acc:.3}", ha="center", va="bottom")

plt.xlabel("特征值组合")
plt.ylabel("准确率")
plt.title("所有特征值组合的准确率")
plt.xticks(rotation=20, ha="right")
plt.tight_layout()
plt.show()
