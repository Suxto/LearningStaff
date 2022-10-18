How to make it: Tutorial
Tags: Math
Time Created: November 6, 2021 10:22 PM

- **Intro**
    
    有一个杨辉三角形：从上往下，从左往右都从第0行（列）开始计数。给出目标位置的行和列（以及结果的模数），求从顶端到目标位置的最短路径（每次可以向下或者右下移动）。
    
- Detail
    
    [https://vj.ppsucxtt.cn/783cd9ace4c3d3ed960db535140a882c?v=1635727673](https://vj.ppsucxtt.cn/783cd9ace4c3d3ed960db535140a882c?v=1635727673)
    
    Figure 1 shows the Yang Hui Triangle. We number the row from top to bottom 0,1,2,…and the column from left to right 0,1,2,….If using C(n,k) represents the number of row n, column k. The Yang Hui Triangle has a regular pattern as follows.C(n,0)=C(n,n)=1 (n ≥ 0)C(n,k)=C(n-1,k-1)+C(n-1,k) (0<k<n)Write a program that calculates the minimum sum of numbers passed on a route that starts at the top and ends at row n, column k. Each step can go either straight down or diagonally down to the right like figure 2.As the answer may be very large, you only need to output the answer mod p which is a prime.
    
    **Input**
    
    Input to the problem will consists of series of up to 100000 data sets. For each data there is a line contains three integers n, k(0<=k<=n<10^9) p(p<10^4 and p is a prime) . Input is terminated by end-of-file.
    
    **Output**
    
    For every test case, you should output "Case #C: " first, where C indicates the case number and starts at 1.Then output the minimum sum mod p.
    
    **Sample Input**
    
    `1 1 2
    4 2 7`
    
    **Sample Output**
    
    `Case #1: 0
    Case #2: 5`
    

**思路：**

本题的思路很简单，经过观察，我们可以得到有两种走的路径：

![Untitled](Problems/Problems/杨辉三角形之路（组合数Pro）%209390d8eb57c1424386d0b31ed3555deb/Untitled.png)

第一种，当b>a-b的时候，先向下，然后斜着走。

第二种，先沿着外面走，然后遇到数组下来。

于是，我们计算第一种的时候就可以用组合数的性质，快速的完成计算$\binom{n}{m}=\binom{n-1}{m}+\binom{n-1}{m-1}$。

用这个递推式，我们可以很轻松的得出，如果最后的结果就是竖直方向上的1加上$C^b_{a+1}$（也就是目标数正下方的数）

组合数的求模使用卢卡斯定理，要预处理阶乘的质数模。

注意：这题的实例很多，如果不进行预处理的话，会TLE！

     预处理的方法，将范围内的质数的阶乘模全部求出来。

- Slove
    
    1）先走最外侧的斜边，然后竖直向下走到达c(n,m)
    那么我们会走过m+1个1
    然后还需要加入c(m+1，m)+c(m+2,m)+c(m+3,m)+…+c(n,m)
    然后我先强行加入c(m+1,m+1),然后可以将c(m+1,m)和c(m+1,m+1)合并成c(m+2,m+1)，然后不断向后合并，最终得到c(n+1,m+1),因为我们加入了c(m+1,m+1)=1所以最终的答案是c(n+1,m+1)+m
    2）先竖直向下，然后走斜边
    那么我们会先走过n-m个1
    然后还需要加入c(m,0)+c(m+1,1)+c(m+2,2)+…+c(n,m)
    c(m,0)=c(m+1,0)替换，然后将c(m+1,0),c(m+1,1)合并得到c(m+2,1)，然后不断向后合并最终得到c(n+1,m)
    所以最终的答案是c(n+1,m)+n-m
    然后观察后发现，单独的1的个数多的方案最终的结果会更优。
    所以当n-m>m,选择方案（2），否则选择方案（1）
    
- Code
    
    ```cpp
    #include<bits/stdc++.h>
    using namespace std;
    typedef long long lol;
    
    const int N = 10015;
    lol f[1300][10000];
    bool isnPrime[N];
    int pos[N], cnt = 0, prime[N];
    
    lol quickPow(lol a, lol b, lol mod) {
        lol ans = 1 % mod;
        while (b > 0) {
            if (b & 1) ans = ans * a % mod;
            a = a * a % mod;
            b = b >> 1;
        }
        return ans;
    }
    
    void getPrime() {
        prime[cnt++]=2;
        isnPrime[4]=isnPrime[0] = isnPrime[1] = true;
        isnPrime[2]=false;
        for (int i = 3; i < N; i+=2) {
            if (!isnPrime[i]) {
                pos[i] = cnt;
                prime[cnt++] = i;
            }
            for (int j = 0; j < cnt && prime[j] * i <= N; j++) {
                isnPrime[i * prime[j]] = true;
                if (i % prime[j] == 0) break;
            }
        }
    }
    
    void init() {
        getPrime();
        for (int i: prime) {
            f[pos[i]][0] = 1;
            for (int j = 1; j <= i; j++)
                f[pos[i]][j] = f[pos[i]][j - 1] * j % i;
        }
    }
    
    lol CC(lol n, lol m, lol mod) {
        if (m > n) return 0;
        lol a = f[pos[mod]][n];
        lol b = (f[pos[mod]][m] * f[pos[mod]][n - m]) % mod;
        return (a * quickPow(b, mod - 2, mod)) % mod;
    }
    
    lol Lucas(lol n, lol m, lol mod) {
        if (m == 0) return 1;
        return (CC(n % mod, m % mod, mod) * Lucas(n / mod, m / mod, mod)) % mod;
    }
    
    int main() {
        lol mod, a, b, ans;
        int cas = 0;
        init();
        while (scanf("%lld%lld%lld", &a, &b, &mod) != EOF) {
            if (b > a / 2) b = a - b;//阶乘数只算了一半。
            ans = a - b + mod;
            ans = (ans % mod + Lucas(a + 1, b, mod)) % mod;
            printf("Case #%d: %lld\n", ++cas, ans);
        }
        return 0;
    }
    ```