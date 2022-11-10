#include <iostream>
#include <vector>
#include <queue>


std::vector<std::vector<int>> *creatMatrix(std::vector<std::vector<int>> &v) {
    auto *mat = new std::vector<std::vector<int>>(v.size(), std::vector<int>(v.size()));
    for (int i = 0; i < v.size(); i++) {
        for (const auto &ints: v[i]) {
            (*mat)[i][ints] = 1;
        }
    }
    return mat;
}

void bfs(std::vector<std::vector<int>> &v) {
    std::vector<bool> vis(v.size());
    std::queue<int> queue;
    queue.push(0);
    vis[0] = true;
    while (!queue.empty()) {
        int now = queue.front();
        std::cout << now + 1 << ' ';
        queue.pop();
        for (const auto &i: v[now]) {
            if (!vis[i]) {
                queue.push(i);
                vis[i] = true;
            }
        }
    }
}

void dfs(std::vector<std::vector<int>> *matrix, int now = 0) {
    static std::vector<bool> vis(matrix->size());
    vis[now] = true;
    std::cout << now + 1 << ' ';
    for (int i = 0; i < matrix->size(); i++) {
        if ((*matrix)[now][i] && !vis[i]) {
            dfs(matrix, i);
        }
    }
//    vis[now] = false;
}

int main() {
    std::cout << "Enter the number of vertex and node: ";
    int v, e;
    std::cin >> v >> e;
    std::vector<std::vector<int>> table(v);
    for (int i = 0; i < e; i++) {
        int a, b;
        std::cin >> a >> b;
        a -= 1;
        b -= 1;
        table[a].emplace_back(b);
        table[b].emplace_back(a);
    }
    auto matrix = creatMatrix(table);
    std::cout << "BFS the graph:\n";
    bfs(table);
    std::cout << "\nDFS the graph:\n";
    dfs(matrix);
}