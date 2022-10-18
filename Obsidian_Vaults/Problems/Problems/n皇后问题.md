How to make it: Tutorial
Tags: DFS
Time Created: May 4, 2022 11:38 AM

### 题目

n−皇后问题是指将 n 个皇后放在 n×n 的国际象棋棋盘上，使得皇后不能相互攻击到，即任意两个皇后都不能处于同一行、同一列或同一斜线上。

现在给定整数 n，请你输出所有的满足条件的棋子摆法。

**输入格式**
共一行，包含整数 n。

**输出格式**
每个解决方案占 n 行，每行输出一个长度为 n 的字符串，用来表示完整的棋盘状态。

其中 `.` 表示某一个位置的方格状态为空，Q 表示某一个位置的方格上摆着皇后。

每个方案输出完成后，输出一个空行。

**注意**：行末不能有多余空格。

输出方案的顺序任意，只要不重复且没有遗漏即可。

数据范围
1≤n≤9
输入样例：

4
1
输出样例：

```java
.Q..
...Q
Q...
..Q.
```

```java
..Q.
Q...
...Q
.Q..
```

### 分析：

    很明显的dfs问题，通过在每一行防止一个皇后来枚举。

1. 首先建立一个表来记录皇后的位置 `table[N][N]`
2. 皇后可以攻击同一行、列、对角的敌人，建立 `row[N]` `colume[N]` `diag[N]` 和 `opDiag[N]`
3. 在同一个对角线（左上到右下）上的皇后 `x+y` 一致。
4. 在同一个反对角线（右上到左下） `x-y` 一致
5. 每放置一个皇后标记一次上面的几个数组就可以了
6. 记得在递归完成之后将数组恢复原样！

### code

```java
#include <iostream>

using namespace std;

const int N = 20;

int n;
char graph[N][N];
int column[N];
int digonal[N];
int anti_digonal[N];
void dfs(int x){
    if(x>n){
        for(int i = 1;i<=n;i++){
            for(int j = 1;j<=n;j++){
                cout<<graph[i][j];
            }
            cout<<endl;
        }
        cout<<endl;
    }
    for(int i = 1;i<=n;i++){
        if(column[i]==0&&digonal[n+x-i]==0&&anti_digonal[x+i]==0){
            graph[x][i] = 'Q';
            column[i] = 1;
            digonal[n+x-i] = 1;
            anti_digonal[x+i] = 1;
            dfs(x+1);
						//递归完成后恢复原样！
            column[i] = 0;
            digonal[n+x-i] = 0;//+n是为了防止负数下标
            anti_digonal[x+i] = 0;
            graph[x][i] = '.';
        }
    }
}
int main(){
    cin>>n;
    for(int i = 1;i<=n;i++){
        for(int j = 1;j<=n;j++){
            graph[i][j] = '.';
        }
    }
    dfs(1);//递归第一行

    return 0;
}

```