How to make it: Tutorial
Tags: DP
Time Created: May 10, 2022 7:37 PM

[Problem - H - Codeforces](https://codeforces.com/gym/103118/problem/H?f0a28=1)

### 思路：

    很经典的dp问题，本来用了bfs想暴力出来的，没想到出题人留了一手，内存没给够。这道题dp的关键在于使用二维数组同时dp血量和护甲，通过对血量的不断改变，使得总有一款血量适合Yuna。如果，在得到当前金币数量之前死掉的话，并不会被计入dp数组

### code

```cpp
#include <iostream>
#include <queue>
#include <vector>
#include <set>

typedef long long ll;
#define endl '\n'
using namespace std;
#define IO ios::sync_with_stdio(false);\
    cin.tie(0)

ll dp[1000][1000];//health-stamina

int main() {
    IO;
    int num, health, stamina;
    cin >> num >> health >> stamina;
    for (int i = 0; i < num; i++) {
        int a, b;
        ll c;
        cin >> a >> b >> c;
        for (int j = stamina; j >= 0; j--) {
            if (j >= b)
                for (int k = health; k > a; k--) {
                    dp[k][j] = max(dp[k][j], dp[k - a][j - b] + c);
                }
            else
                for (int k = health; k - a - b + j > 0; k--) {
                    dp[k][j] = max(dp[k][j], dp[k - a - b + j][0] + c);
                }
        }
    }
    cout << dp[health][stamina];
}
```