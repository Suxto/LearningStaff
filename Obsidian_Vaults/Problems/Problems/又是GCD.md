How to make it: Tutorial
Tags: Math
Time Created: August 7, 2022 2:19 PM

[https://codeforces.com/contest/1708/problem/B](https://codeforces.com/contest/1708/problem/B)

    一个B题没做出来，也没啥好说的，菜就完事了。

    当我们要找一个数的在一个范围里最小倍数的时候，可以通过 $Obj=\lfloor \frac{range-1}{i}\rfloor\times i$ 得到

```cpp
#include <bits/stdc++.h>

#define MAXN 200020
#define int long long
#define endl '\n'
#define IO cin.tie(nullptr)->sync_with_stdio(0)
using namespace std;

void solve() {
  int n, l, r;
  cin >> n >> l >> r;
  vector<int> v;
  for (int i = 1; i <= n; i++) {
    int t = ((l - 1) / i + 1) * i;
    if (__gcd(t, i) == i && t >= l && t <= r) {
      v.emplace_back(t);
    } else {
      cout << "NO\n";
      return;
    }
  }
  cout << "YES\n";
  for (int &i : v) {
    cout << i << ' ';
  }
  cout << endl;
}

signed main() {
  IO;
  int T = 1;
  cin >> T;
  while (T--)
    solve();
}
```