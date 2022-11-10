#include <iostream>
#include <functional>
#include <vector>
#include <queue>

using namespace std;

ostream &space(ostream &o, int x) {
    for (int j = 0; j < x; j++) {
        o << "  ";
    }
    return o;
}


int main() {
    int n;
    cin >> n;
    vector<vector<int>> tree(n + 1);
    char tmp[3];
    for (int i = 1; i < n; i++) {
        cin >> tmp;
        int x = tmp[0] - 'A' + 1;
        cin >> tmp;
        int y = tmp[0] - 'A' + 1;
        tree[x].emplace_back(y);
        tree[y].emplace_back(x);
    }
    vector<int> bin(30, -1);

    auto bfs = [&](int fst) {
        vector<int> vis(n + 1);
        std::queue<int> q;
        q.push(fst);
        vis[fst] = fst;
        bin[fst] = fst;
        while (!q.empty()) {
            int now = q.front();
            int pos = vis[now];
            q.pop();
            int degree = (int) tree[now].size();
            if (degree) {
                bool first = true;
                for (const int &i: tree[now]) {
                    if (vis[i]) continue;
                    if (first) pos = pos << 1, first = false;
                    else pos = (pos << 1) + 1;
                    bin[pos] = i;
                    vis[i] = pos;
                    q.push(i);
                }
            }
        }
    };
    bfs(1);
//
//    for (int i = 1; i <= 11; i++) cout << i << ' ';
//    cout << endl;
//    for (int i = 1; i <= 11; i++) cout << (char) (bin[i] + 'A' - 1) << ' ';
    function<void(int, int)> dfs = [&](int pos, int sp) {
        int l = pos << 1;
        int r = l + 1;
        space(cout, sp) << (char) (bin[pos] + 'A' - 1) << endl;
        if (bin[l] != -1) dfs(l, sp + 2);
        if (bin[r] != -1) dfs(r, sp);
    };
    dfs(1, 0);
    return 0;
}
/*
7
A B
A C
A D
B E
B F
C G
*/