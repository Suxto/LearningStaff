#include<iostream>
#include<queue>
#include<array>
#include <cmath>
#include <set>

using namespace std;

constexpr array<int, 4> dr{1, -1, 0, 0}, dc{0, 0, 1, -1};

using grid = array<array<int, 3>, 3>;
using pii = pair<int, int>;
array<pii, 9> pos;
set<int> vis;
grid obj;

struct node {
    grid g;
    int step;
    int val = 0;
    int od = 0;
    pii zero;

    explicit node(grid grid, int s) : g(grid), step(s) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                od *= 10;
                od += grid[i][j];
                auto [r, c] = pos[grid[i][j]];
                val += abs(i - r) + abs(j - c);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!grid[i][j]) {
                    zero = {i, j};
                    goto done;
                }
            }
        }
        done:;
    }


    bool operator<(const node &n) const {
        return this->val > n.val;
    }

    [[nodiscard]] bool done() const {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (g[i][j] != obj[i][j])return false;
//            }
//        }
//        return true;
        return val == 0;
    }

};

istream &operator>>(istream &is, grid &g) {
    for (auto &r: g) {
        for (auto &x: r) is >> x;
    }
    return is;
}


ostream &operator<<(ostream &os, grid &g) {
    for (auto &r: g) {
        for (auto &x: r) {
            os << x << ' ';
        }
        os << '\n';
    }
    return os;
}


template<typename T>
void extend(T &q, node &now) {
    auto [r, c] = now.zero;
    for (int i = 0; i < 4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];
        if (nr < 0 || nr > 2 || nc < 0 || nc > 2) continue;
        grid new_grid = now.g;
        swap(new_grid[r][c], new_grid[nr][nc]);
        node new_node(new_grid, now.step + 1);
        if (vis.count(new_node.od)) continue;
        vis.insert(new_node.od);
        q.emplace(new_node);
    }
}

pair<int, bool> best_first(grid ori) {
    int tries = 0;
    vis.clear();
    priority_queue<node> pq;
    pq.emplace(ori, 0);
    while (!pq.empty()) {
        tries++;
        auto now = pq.top();
        if (pq.top().done()) {
            return {tries, false};
        }
        pq.pop();
        extend(pq, now);
    }
    return {tries, true};
}

pair<int, bool> breadth_first(grid ori) {
    int tries = 0;
    vis.clear();
    queue<node> q;
    q.emplace(ori, 0);
    while (!q.empty()) {
        tries++;
        auto now = q.front();
        if (q.front().done()) {
            return {tries, false};
        }
        q.pop();
        extend(q, now);
    }
    return {tries, true};
}

void get_pos() {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            pos[obj[i][j]] = {i, j};
        }
    }
}

int main() {
    grid ori;
    cout << "Input original and objective state:\n";
    cin >> ori >> obj;
    get_pos();
    auto [t, f] = breadth_first(ori);
    cout << "Breadth first algorithm takes " << t << " tries to " << (f ? "fail" : "success") << '\n';
    tie(t, f) = best_first(ori);
    cout << "Best first algorithm takes " << t << " tries to " << (f ? "fail" : "success") << '\n';
}

/*
2 8 3
1 0 4
6 7 5

1 2 3
8 0 4
7 6 5

-----------------
 1 2 3
 4 5 6
 7 8 0

 0 1 2
 3 4 5
 6 7 8

-----------------
2 8 3
1 6 4
7 0 5

1 2 3
8 0 4
7 6 5
 */