How to make it: Tutorial
Tags: Math
Time Created: August 16, 2022 4:32 PM

[https://ac.nowcoder.com/acm/contest/33194/E](https://ac.nowcoder.com/acm/contest/33194/E)

题目给出一个数字 $n$，要求构造一个序列，使得序列中的最长上升自序列有 $n$ 个。

**思路：**

需要注意的是， `LIS` 不一定是连续的，于是对于一个串的最大效率利用就是将其两两分组，例如：

$2,1,4,3,6,5\dots ,2n,(2n-1)$ 这样构建出来的串，将两个数看作一个节点，于是每个节点都能有两个选择。于是选择的个数合计起来就是 $2^n$ ($n$ 是节点的个数) 。于是顺着这个思路往下走，我们可以将给的数字进行二进制拆分，在合适的地方加入一些更大的数字来达到总数的要求。比如$(37)_{10}=(100101)_2$  先构建 32 个 `LIS` 的串作为基本序列，我们记最高位的位置为 $len$ 此处$len=5$ ，基本序列需要用到 $10$ 个数（5个节点），然后我们在这些数的基础上插入一些其他的数，比如构建 $(4)_{10}=(100)_2$ 。构建这个需要有两个节点，于是插入在第二个节点之后，在这个位置插入 `LIS` 串长度$-$2 （加上前两个节点和其他 `LIS` 一样长）个数就行。接着我们处理下一个需要额外构建的数 $(1)_{10}=(1)_2$ ，对于这个我们只需要在原有的串上加入$1$ 个相同长度的 `LIS` 就行，于是将需要的数字加在第一个节点之前，然而在前面加入额外 $4$ 个 `LIS` 的时候我们已经加上了$3$  个数，在这题中 `LIS` 的长度应该是 $len$（有 $len$ 个节点），我们只需要加上 $len-3$ 就行（和前面加上的连成 `LIS` 节约位数）。对于之前加上的这些数，我们用一个变量 $cnt$ 来记录加入的位数。为了对接加上的这些数，在原串的后面加上一个数 $2\times len+cnt+1$ 就完成了整个串的构建。

- Code
    
    ```cpp
    #include <bits/stdc++.h>
    
    #define MAXN 100010
    #define int long long
    #define endl '\n'
    using namespace std;
    void solve() {
        int n, len = 30, cnt = 0;
        cin >> n;
        vector<int> ins(len);
        while (!((n >> len) & 1))
            len--;
        for (int i = len - 1; i >= 0; i--) {
            if ((n >> i) & 1) {
                // (len - i) represents the choices behind current position
                ins[i] = len - i - cnt;
                cnt += ins[i];
            }
        }
        int base = (len << 1) + 1;
        cout << base + cnt << endl;
        for (int i = 0; i < len; i++) {
            while (ins[i]--) {
                cout << base++ << ' ';
            }
            cout << (i << 1) + 2 << ' ' << (i << 1) + 1 << ' ';
        }
        cout << base << endl;
    }
    
    signed main() {
        int T = 1;
        std::cin >> T;
        while (T--) {
            solve();
        }
        return 0;
    }
    ```