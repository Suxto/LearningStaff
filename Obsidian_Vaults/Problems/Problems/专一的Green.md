How to make it: Tutorial
Tags: Math
Time Created: October 13, 2021 11:22 PM

“不不不，这是昨天的妹子，不是今天的！”女生缘敲好的Gree说道。鸡尾酒又被秀了一脸，小肚鸡肠的他将妹子记录了下来，每个妹子都用不同的一个数字表示。 “我花心？？我每天都对一个妹子专一好不好！”Gree辩解道。于是鸡尾酒每天都可以记录一个数字，代表今天Gree秀他的对象。Gree学长总共秀了n天，忍无可忍的鸡尾酒想知道，对于某一天ai，从第ai天到第n天这一段时间内，他还要被多少个不同的妹子秀。

Input 第一行输入两个整数n,m(1 ≤ n,m ≤ 10^5)，n代表天数，m代表询问数。 接下来一行包含n个数字，分别代表每天鸡尾酒记录的妹子编号。（编号<=1e5） 接下来包含m行，代表m次询问。每次询问包含一个整数 ai ,(1<=ai <=n)

Output 总共输出m行，对于每一个询问ai，输出一行一个整数代表从第ai天到第n天的区间内（闭区间）有多少个不同的数字。

### Examples:

**Input**

```
10 10
1 2 3 4 1 2 3 4 100000 99999
1
2
3
4
5
6
7
8
9
10
```

**Output**

```
6
6
6
6
6
5
4
3
2
1
```

```
ps.如果直接遍历的话时间开销太大，直接超时。
换一种思路，在输入时记录每一个号码出现的次数。
```

AC:

```
#include <stdio.h>#include <string.h>int num[100005];int day[100005];int main() {    int m, n, i, t, c = 0;    memset(num, 0, sizeof(num));    scanf("%d%d", &n, &m);    for (i = 0; i < n; i++) {        scanf("%d", &day[i]);        if (num[day[i]] == 0) c++;        num[day[i]]++;    }    for (i = 0; i < n; i++) {        t=day[i];        day[i]=c;        num[t]--;        if(num[t]==0) c--;    }    for (i = 0; i < m; i++) {        scanf("%d", &t);        if(i!=m-1)printf("%d\n",day[--t]);        else printf("%d",day[--t]);    }}
```