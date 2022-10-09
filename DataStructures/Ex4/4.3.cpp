#include<iostream>
#include <functional>
#include <vector>

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
    vector<vector<int>> tree(n);
    char tmp[3];
    for (int i = 1; i < n; i++) {
        cin >> tmp;
        int x = tmp[0] - 'A';
        cin >> tmp;
        int y = tmp[0] - 'A';
        tree[x].emplace_back(y);
        tree[y].emplace_back(x);
    }
    function<void(int, int, int)> dfs = [&](int now, int pre, int lay) {
        space(cout, lay) << static_cast<char>(now + 'A') << endl;
        for (int &i: tree[now]) {
            if (i == pre) continue;
            dfs(i, now, lay + 1);
        }
    };
    dfs(0, 0, 0);
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