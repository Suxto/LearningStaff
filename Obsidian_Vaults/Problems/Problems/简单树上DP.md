How to make it: Tutorial
Tags: DP
Time Created: August 19, 2022 6:55 PM

[https://ac.nowcoder.com/acm/contest/38727/H](https://ac.nowcoder.com/acm/contest/38727/H)

**思路**：

    对于这个问题，它要求的是结果的末尾0，那么经过分析，显然，只有当有10为因子的时候才能增加一个0，于是我们可以计算所有元素中的 $2$ 和 $5$ 的因子个数，然后这两个数的最小值就是能产生的0的个数。

    有一点需要注意的是： 2和5要分开 `DP` 如果在统计阶段就将某个节点的因子数量进行取小，就会导致当处于同一条路线的 2 和 5 不能进行叠加，导致最后的答案会只统计到含因子10的节点。在最后对两个 `DP` 取小，相当于取两个集合的交集。

    同时，通过树上 `DP` 可以节约回溯的时间，当前的答案就是当前节点的父亲的答案减去当前节点的子树大小（减去父节点在当前子树计算得到的 2 或者 5 的个数） 加上当前节点子树大小乘以当前节点的2或5的个数。最后将2和5的结果取小就是10的个数，也就是后缀0的个数。

$$
⁍
$$

### 代码

```cpp
#include <bits/stdc++.h>

//#define debug
#define MAXN 100010
#define int long long
#define double long double
#define two first
#define five second
#define dbg(x) std::cout << #x << " = " << x << std::endl
#define endl '\n'
using namespace std;
typedef pair<int, int> pii;

ostream &operator<<(ostream &o, const pii &p) {
    o << '<' << p.first << ',' << p.second << '>';
    return o;
}

template <typename T> ostream &operator<<(ostream &o, vector<T> &x) {
    o << "[";
    for (auto i = x.begin(); i < x.end(); ++i) {
        if (i != x.begin())
            o << ", ";
        o << *i /*<< ' '*/;
    }
    o << "]" << endl;
    return o;
}

vector<int> siz;
vector<pii> dp;
vector<pii> dis;
vector<vector<int>> tree;

void dfs(int now, int pre) {
    siz[now] = 1;
    for (int &i : tree[now]) {
        if (i != pre) {
            dfs(i, now);
            siz[now] += siz[i];
        }
    }
    int tmp = now, cnt = 0;
    while (tmp % 2 == 0) {
        cnt++;
        tmp >>= 1;
    }
    dis[now].two = cnt;
    cnt = 0;
    while (tmp % 5 == 0) {
        cnt++;
        tmp /= 5;
    }
    dis[now].five = cnt;
}

void dfss(int now, int pre) {
    dp[now].two = dp[pre].two + siz[now] * (dis[now].two - dis[pre].two);
    dp[now].five = dp[pre].five + siz[now] * (dis[now].five - dis[pre].five);
    for (int &i : tree[now]) {
        if (i != pre)
            dfss(i, now);
    }
}

void solve() {
    int n, que;
    cin >> n >> que;
    siz = vector<int>(n + 1);
    dp = dis = vector<pii>(n + 1);
    tree = vector<vector<int>>(n + 1);
    for (int i = 1; i < n; i++) {
        int a, b;
        cin >> a >> b;
        tree[a].emplace_back(b);
        tree[b].emplace_back(a);
    }
    dfs(1, 0);
    //    cout << "size:\n" << siz;
    dfss(1, 0);
    //    cout << "dis:\n" << dis;
    while (que--) {
        int now;
        cin >> now;
        cout << min(dp[now].two, dp[now].five) << endl;
    }
}

signed main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin.exceptions(std::istream::failbit);
    cout.tie(nullptr);
    int T = 1;
    // std::cin >> T;
    while (T--) {
        solve();
    }
    return 0;
}
```