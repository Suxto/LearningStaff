# A-Good-String

How to make it: Tutorial
Tags: Enumerate
Time Created: October 24, 2021 1:34 PM

### 题目：

给你一个长度为n的字符串，其中n保证为2的整数幂（ $n = 2^k，n=2k$ ）
当一个字符串s至少满足以下条件之一时，它被称为 *cc-good* 字符串 ：

• s的长度为1且它由字符c组成 $(i.e. s_1=cs1=c)$；

• s的长度大于1且它的前半段完全由字符c组成 $(i.e. s_1=s_2=\dots=s_{\frac{n}{2}}=cs1=s2=⋯=s2n=c)$ ，而后半段 $(i.e. the string s_{\frac{n}{2} + 1}s_{\frac{n}{2} + 2} \dots s_ns2n+1s2n+2…sn)$ 则是一个 $(c+1)(c+1)-good$ 字符串；

• s的长度大于1且它的后半段完全由字符c组成 $(i.e. s_{\frac{n}{2} + 1}=s_{\frac{n}{2} + 2}=\dots=s_n=cs2n+1=s2n+2=⋯=sn=c)$ ，而前半段 $(i.e. the string s_1s_2 \dots s_{\frac{n}{2}}s1s2…s2n)$ 则是一个$(c+1)-good$ 字符串。

例如："aabc" 是一个 *'a'-good* 字符串， "ffgheeee" 是一个 *'e'-good* 字符串。
在一次操作中，你可以从1到n选择一个索引i，将$s_i$替换成你希望的任何一个小写字母 ('a' - 'z')。
你的任务是找到最少的操作次数，使一个字符串转换成 *'a'-good* 字符串 (i.e. *cc-good* 字符串 且 *c*= 'a')。（可以证明答案一定存在）

**Input**

第一行输入一个整数t ($1 \le t \le 2 \cdot 10^4$) — 测试样例的数量。 接下来是t组测试样例。
每组测试样例的第一行包含一个整数 $n (1 \le n \le 131~0721≤n≤131 072)$ — 字符串s的长度，数据满足 $n = 2^kn=2k$ （k为非负整数）。第二行包含由n个小写字母组成的字符串s。
数据保证所有的测试样例的n相加不会超过 $2 \cdot 10^52⋅105 (\sum n \le 2 \cdot 10^5)$.

**Output**

对于每组测试样例输出答案。

- **Example**
    
    ```cpp
    **Input**
    6
    8
    bbdcaaaa
    8
    asdfghjk
    8
    ceaaaabb
    8
    bbaaddcc
    1
    z
    2
    ac
    **Output**
    0
    7
    4
    5
    1
    1
    ```
    

### 思路：

从题目的描述可以看出使用分治的方法，将一个字符串分成两份第一份全为一样的字母，另一份用递归的方式重复之前的过程

![1.png](1.png)

只要用枚举法算出原字符串与每一种相同长度的合法标准字符串，相差的位数就可以了。每次递归分支的时候取最小的（减少计算量）。

### AC代码：

```cpp
#include<iostream>
using namespace std;
char s[200000];

int chk(int l, int r, char now, int pre) {
    if (l == r) {
        if (s[l] == now) return pre;
        else return pre + 1;
    }
    int mid = (l + r) / 2;

    int errl = 0, errr = 0;
    for (int i = l;i <= mid;++i)
        if (s[i] != now) errl++;
    int nextl = chk(mid + 1, r, now + 1, pre + errl);

    for (int i = mid + 1;i <= r;++i)
        if (s[i] != now) errr++;
    int nextr = chk(l, mid, now + 1, pre + errr);

    if (nextl <= nextr) return nextl;
    else return nextr;
}

int readc() {
    char ch = getchar();
    if (ch == '\n' || ch == '\r' || ch == ' ') return readc();
    else return ch;
}

int main() {
    int n, l;
    cin >> n;
    while (n--) {
        cin >> l;
        if (l == 1) {
            if (readc() == 'a') cout << '0' << endl;
            else cout << '1' << endl;
            continue;
        }
        scanf("%s", s);
        int i = chk(0, l - 1, 'a', 0);
        cout << i << endl;
    }
    return 0;
}
```