How to make it: Tutorial
Tags: Math
Time Created: October 29, 2021 10:35 PM

- **Intro**
    
    你现在拥有一个数组 a*a* 包含 n*n* 个正整数。
    
    最初，你还有一个整数 x = 0*x*=0. 每次操作，你可以选择下面两个操作之一：
    
    1. 在 1 到 *n* 之间选择 **一个** *i* 然后将 *ai* 增加 *x* (*ai*=*ai*+*x*), 随后将 *x* 增加 1 (*x*=*x*+1).。
        
        $a_i = a_i + x$$,x = x + 1$
        
    2. 只将 *x* 增加 1 (*x*=*x*+1)。
        
        $x = x + 1$
        
    
    操作1对从 1 到 *n* 之间之间的每个 $a_i$ 最多只能进行一次。
    
    现在，SQ希望你找出将这个数组改造成每个元素都是 *k* 的整数倍数的最少操作次数 ( *k* 会给出)。
    
    你需要回答t组独立的测试样例。
    
- **Input**
    
    第一行包含一个正整数 t*t* (1 \le t \le 2 \cdot 10^41≤*t*≤2⋅104) — 测试样例的数量。 接下来是 t*t* 组测试样例。
    每个测试样例的第一行包含两个整数 n*n* 和 k*k* (1 \le n \le 2 \cdot 10^5; 1 \le k \le 10^91≤*n*≤2⋅105;1≤*k*≤109) — 数组 a*a* 的长度和需要的因数。第二行测试样例包含 n*n* 个正整数 a_1, a_2, \dots, a_n*a*1,*a*2,…,*an* (1 \le a_i \le 10^91≤*ai*≤109), 其中 a_i*ai* 是a*a*数组的第 i*i* 个元素。
    数据保证所有测试样例的 n*n* 相加不超过 2 \cdot 10^52⋅105 (\sum n \le 2 \cdot 10^5∑*n*≤2⋅105).
    
- **Output**
    
    Shown in the example
    
- **Example**
    
    **Input**
    
    ```
    5
    4 3
    1 2 1 3
    10 6
    8 7 1 8 3 7 5 10 8 9
    5 10
    20 100 50 20 100500
    10 25
    24 24 24 24 24 24 24 24 24 24
    8 8
    1 2 3 4 5 6 7 8
    
    ```
    
    **Output**
    
    ```
    6
    18
    0
    227
    8
    ```
    

### 思路：

实际上，不管是否将x加到某一个数上面，x都会加一，所以，如果发现一个数已经是k的倍数，就不需要动他。于是我们对剩下的数进行操作。

- 一个最简单的思路就是将当前的x与每一个不是x倍数的数相加，看看有没有能够到达x倍数的。如果有那么将这个x给它加上去，然后删掉当前的数，x++。如果没有的话就直接给x++，其实这个遍历耗时还好，但是真正耗时的是当x很小但是k很大的时候有好几轮毫无意义的遍历，于是毫无意外的TLE了。
- 那既然我们不能一直遍历，那我们能不能知道一共需要将x加多少次以减少空转的次数。于是我们回到题目本身：通过 $d=k-(a_i \bmod k)$ 就可以计算出当前的$a_i$需要加几才能成为$k$的倍数（或者加上 $p=d+ nk (n\in N^+)$）。于是，我们只要在读入的时候记录$d$，然后找到序列中出现最多的$d_{most-shown}$（相同个数的时候取最大的）并记录它的个数$maxcnt$，于是可以用$x=(maxcount-1)k+d_{max-shown}+1$ 得到x。
- **shit-code（真-枚举）**
    
    ```cpp
    #include<bits/stdc++.h>
    //change the array
    using namespace std;
    
    int main() {
        ios::sync_with_stdio(false);
        cin.tie(0);
        int n;
        cin >> n;
        while (n--) {
            vector<long> arr;
            arr.reserve(1e5);
            int x = 1;
            long num, k;
            long temp;
            cin >> num >> k;
            while (num--) {
                cin >> temp;
                if (temp % k) arr.emplace_back(temp);
            }
            if (arr.empty()) {
                cout << '0' << endl;
                continue;
            }
            //sort(arr.begin(),arr.end());
            bool pop;
            while (!arr.empty()) {
                pop = false;
                for (auto i = arr.begin(); i < arr.end(); i++) {
                    if (!(((*i) + x) % k)) {
                        swap(*i, *(arr.end() - 1));
                        arr.pop_back();
                        pop = true;
                        x++;
                    }
                    //x++;
                }
                if (!pop) x++;
            }
            cout << x << endl;
            printf("Time used = %.12f\n", (double)clock() / CLOCKS_PER_SEC);
        }
        cin.get();cin.get();
    }
    ```
    
- **good-code（眼睛不需要的话可以捐给有需要的人）**
    
    ```cpp
    #include<bits/stdc++.h>
    
    typedef long long lll;
    using namespace std;
    
    vector<lll> arr;
    
    int main() {
        ios::sync_with_stdio(false);
        cin.tie(0), cout.tie(0);
        lll n;
        cin >> n;
        arr.reserve(3e5);
        while (n--) {
            arr.clear();
            lll num, k;
            lll temp;
            cin >> num >> k;
            while (num--) {
                cin >> temp;
                temp %= k;
                if (temp)
                    arr.push_back(k - temp);
            }
    
            if (arr.empty()) {
                cout << '0' << endl;
                continue;
            }
    
            sort(arr.begin(), arr.end());
            lll count = 0ll, maxct = 0ll, maxsh;
            lll lenth=arr.size();
            for (lll i = 0ll; i < lenth; i++) {
                count++;
                if ((arr[i] != arr[i + 1ll] /*&& i < arr.size() - 1*/) || i == arr.size() - 1ll) {
                    if (count > maxct || (count == maxct && maxsh < arr[i])) {
                        maxct = count;
                        maxsh = arr[i];
                    }
                    count = 0ll;
                }
            }
            lll ans = ((maxct - 1ll) * k + maxsh + 1ll);
            cout << ans << endl;
        }
    }
    ```