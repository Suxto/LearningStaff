How to make it: Tutorial
Tags: DP
Time Created: September 22, 2022 9:50 PM

[Problem - D - Codeforces](https://codeforces.com/contest/608/problem/D)

**思路**：

    由于一次操作可以干掉一个回文串，所以我们需要枚举一下有多少个回文串。显然直接枚举的话会寄，于是我们可以通过区间dp来降低时间复杂度。我们可以通过枚举区间来达到更快的效果，下面是转移方程。

    如果枚举的区间左右边界是一样的( `arr[l]==arr[r]` )，就可以进行优化：

1. 当 `r=l+1` 的时候，说明这是一个回文串且没有优化空间，那么直接设置 `range[l][r]=1`
2. 当上面等式不成立的时候，我们可以将 `range[l][r]` 设为 当前值和 `range[l+1][r-1]` 的最小值，因为如果内部是一个回文串的时候，就可以谁便把两边的 `l` 和 `r` 带走（最后消去的一定是回文串）

做完上面的操作之后，将区间之内的和都捋一便，之前做的改变能传递到后面的结果上。令 `range[l][r] = min(range[l][r], range[l][k] + range[k+1][r])`  $k\in [l,r)$ 因为是取小，如果k截到的位置不是回文串，就不会影响最终结果。

- Code
    
    ```cpp
    #include<bits/stdc++.h>
    
    using namespace std;
    #define MAXN 1000000
    #define endl '\n'
    #define int long long
    typedef pair<int, int> pii;
    
    void solve() {
        int n;
        cin >> n;
        vector<vector<int>> range(n, vector<int>(n,0x3f3f3f3fll));
        vector<int> arr(n);
        for (int i = 0; i < n; i++) {
            cin >> arr[i];
            range[i][i] = 1;
        }
        for (int i = 2; i <= n; i++) {//length
            for (int l = 0; l + i - 1 < n; l++) {
                int r = l + i - 1;
                if (arr[l] == arr[r]) {
                    if (r - l == 1)range[l][r] = 1;
                    else range[l][r] = min(range[l + 1][r - 1], range[l][r]);
                }
                for (int k = l; k < r; k++) {
                    range[l][r] = min(range[l][r], range[l][k] + range[k + 1][r]);
                }
            }
        }
        cout << range[0][n - 1];
    }
    
    signed main() {
        ios::sync_with_stdio(false);
        cin.tie(nullptr);
        cout.tie(nullptr);
        int T = 1;
    //    cin >> T;
        while (T--) {
            solve();
        }
    }
    ```