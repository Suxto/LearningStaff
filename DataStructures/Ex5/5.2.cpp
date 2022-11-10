#include <iostream>
#include <vector>
#include <queue>
#include <numeric>
#include <algorithm>

struct node {
    node(int a, int b, int c) : x(a), y(b), weight(c) {}

    int x, y;
    int weight;
};

int find(std::vector<int> &p, int x) {
    if (p[x] == x)return x;
    else return p[x] = find(p, p[x]);
}

void merge(std::vector<int> &p, int x, int y) {
    p[find(p, x)] = find(p, y);
}

int main() {
    std::cout << "Enter the number of vertex and edges: ";
    int v, e;
    std::cin >> v >> e;
    std::vector<int> p(v + 1);
    std::vector<node> edges;
    std::iota(p.begin(), p.end(), 0);
    for (int i = 0; i < e; i++) {
        int a, b, c;
        std::cin >> a >> b >> c;
        edges.emplace_back(a, b, c);
    }
    std::sort(edges.begin(), edges.end(), [](node &a, node &b) {
        return a.weight < b.weight;
    });
    std::vector<node> ans;
    for (const node &n: edges) {
        if (ans.empty()) {
            ans.emplace_back(n);
            merge(p, n.x, n.y);
        } else {
            if (find(p, n.x) == find(p, n.y)) continue;
            merge(p, n.x, n.y);
            ans.emplace_back(n);
        }
    }
    std::cout << ans.size();
    if (ans.size() >= v - 1) {
        std::cout << "The minimum spanning tree is:\n";
        for (auto &n: ans) {
            std::cout << n.x << ' ' << n.y << std::endl;
        }
    } else std::cout << "There is no minimum spanning tree in this graph.";

}
//test data
//6 10
//1 2 16
//1 5 19
//1 6 21
//2 6 11
//2 3 5
//2 4 6
//3 4 6
//4 6 14
//4 5 18
//5 6 33