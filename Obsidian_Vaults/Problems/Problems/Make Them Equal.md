How to make it: Tutorial
Tags: Math
Time Created: October 26, 2021 12:03 AM

- Intro
    
    Theo has a string $s_1 s_2 \dots s_ns1s2…sn$ and a character c. He wants to make all characters of the string equal to c*c* using the minimum number of operations.
    In one operation he can choose a number $x (1 \le x \le n)$ and **for every position *i***, where $i$ is **not** divisible by x, replace $s_isi$ with c*c*.
    Find the minimum number of operations required to make all the characters equal to c*c* and the x*x*-s that he should use in his operations.
    

- **Input**
    
    The first line contains a single integer t $(1 \le t \le 10^4)$ — the number of test cases.
    The first line of each test case contains the integer n $(3 \le n \le 3 \cdot 10^5)$ and a lowercase Latin letter c*c* — the length of the string s*s* and the character the resulting string should consist of.
    The second line of each test case contains a string s*s* of lowercase Latin letters — the initial string.
    It is guaranteed that the sum of *n* over all test cases does not exceed $3 \cdot 10^5$.
    
- **Output**
    
    For each test case, firstly print one integer m*m* — the minimum number of operations required to make all the characters equal to c*c*.
    Next, print m*m* integers $x_1, x_2, \dots, x_m (1 \le x_j \le )$ — the x*x*-s that should be used in the order they are given.
    It can be proved that under given constraints, an answer always exists. If there are multiple answers, print any.
    
- **Example**
    
    ```cpp
    **Input**
    3
    4 a
    aaaa
    4 a
    baaa
    4 b
    bzyx
    **Output**
    0
    1
    2
    2 
    2 3
    ```
    
- **Note**
    
    Let's describe what happens in the third test case:
    1. $x_1 = 2,x1=2:$ we choose all positions that are not divisible by 22 and replace them, $i. e. bzyx \rightarrow bzbx$;
    2. x_2 = 3*x*2=3: we choose all positions that are not divisible by 33 and replace them, $i. e. bzbx \rightarrow bbbb.$
    

### 题目简介

要通过最小的操作（将无法将x整除序号的项变为目标项），将字符串全部变为同一个字母

### 思路

我感觉应该是贪心算法：如果直接选择末项的话，就可以将除末项之外的所有项转变。但是如果末项不是目标字母，就需要分情况讨论：

- 倒数第二项正确：直接输出只操作导数第二项（操作一次）
- 倒数第二项不正确 **重头戏** （倒数两项都不对）：
    - 枚举所有序号和他们的所有倍数的项（将乘法看作迭代的加法）
    - 如果存在有一个序号的所有倍数均正确（在这条线上的所有项都不需要改变），就输出操作一次同时输出当前序号
    - 如果找不到这样一个序号，就直接输出最后两项（100%改变所有项）

不全是贪心算法，在贪心的同时（找到末项可以一次改变除末项以外的全部项），还要通过枚举判断是否存在一个所有倍数的项都是正确的序号（有的话得输出）

### 最不重要的代码环节：

```cpp
#include <bits/stdc++.h>

using namespace std;

int s[300005];
//int po[300000];

int readc() {
    char ch = getchar();
    if (ch == ' ' || ch == '\n') return readc();
    else return ch;
}

int main() {
    int n, l;
    char ch;
    cin >> n;
    while (n--) {
        int c = 0;
        int h = 0, lh = 0;
        int f;
        cin >> l;
        ch = readc();
        for (int i = 1; i <= l - 2; i++) {
            s[i] = readc();
            if (s[i] != ch)
                c++;
        }
        for (int i = l - 1; i <= l; i++) {
            s[i] = readc();
            if (s[i] != ch) {
                if (i == l - 1) {
                    c++;
                    lh = 1;
                } else {
                    c++;
                    h = 1;
                }
            }
        }
        if (!c) {
            cout << '0' << endl;
            continue;
        }
        if (h == 0) {
            cout << 1 << endl << l << endl;
        } else {
            if (lh == 1) {
                for (int i = 2; i <= l; i++) {  **//key!!!!!!!!!**
                    f = 1;
                    for (int j = i; j <= l; j += i) {
                        if (s[j] != ch) {
                            f = 0;
                            break;
                        }
                    }
                    if (f) {
                        f = i;
                        break;
                    }
                }
                if (f > 1)
                    cout << '1' << endl << f << endl;
                else {
                    cout << 2 << endl;
                    printf("%d %d\n", l - 1, l);
                }
            } else {
                cout << 1 << endl << l - 1 << endl;
            }
        }

    }

}
```