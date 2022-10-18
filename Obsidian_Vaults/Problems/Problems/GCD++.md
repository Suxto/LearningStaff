How to make it: Tutorial
Tags: Math
Time Created: June 4, 2022 1:45 PM

[C-GCD_2021年ICPC国际大学生程序设计竞赛暨陕西省第九届大学生程序设计竞赛（正式赛） (nowcoder.com)](https://ac.nowcoder.com/acm/contest/35232/C)

### 思路：

    对某一个因子$x$来说如果它满足$(r/x-(l-1)/x)\ge k$，它就是一个答案。我们二分区间中最大的符合条件的数，再将这个数减去左边界后+1就能得到当前区间内满足的因子个数。不过这个只筛出了从左区间到右区间的个数，从1到左区间的数并没有被筛查，于是我们分成两段二分。

### 思路code

```cpp
#include <bits/stdc++.h>
using namespace std;
using ll = long long;
const int N = 1e6;
ll l, r, k;
bool check(ll x) {
	return (r/x - (l-1)/x)>=k;
}

ll bin(ll lt,ll rt){
    ll ans=lt-1;
    ll L = lt, R = rt;
	while (L<=R) {
		ll mid = (L+R)>>1;
		if (check(mid)) ans = mid, L = mid + 1;
		else R = mid - 1;
	}
    return ans-lt+1;
}
int main() {
	scanf("%lld%lld%lld", &l, &r, &k);
	ll ans = bin(1,l-1)+bin(l,r);
	printf("%lld\n", ans);
	return 0;
}
```

## code2（看不懂）

```cpp
#include <bits/stdc++.h>
using namespace std;
using ll = long long;
const int N = 1e6;
ll l, r, k;
bool check(ll x) {
	return (r/x - (l-1)/x)>=k;
}
int main() {
	scanf("%lld%lld%lld", &l, &r, &k);
	ll ans = 0;
	for (int i=1; i<10; i++) {
		ans += check(i);
	}
	ll L = 10, R = r;
	while (L<=R) {//双指针，相当于
		ll mid = (L+R)>>1;
		if (check(mid)) ans = mid, L = mid + 1;
		else R = mid - 1;
	}
	printf("%lld\n", ans );
	return 0;
}
// 16 17 18 19 20 21 --> 3
//
```