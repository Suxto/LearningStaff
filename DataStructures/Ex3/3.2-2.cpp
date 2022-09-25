#include<iostream>

using namespace std;
bool vis[9][9];
const int dx[] = {2, 2, -2, -2, 1, 1, -1, -1};
const int dy[] = {1, -1, 1, -1, 2, -2, 2, -2};
typedef pair<int, int> pii;

ostream &operator<<(ostream &o, pii p) {
    o << '(' << p.first << ", " << p.second << ")  ";
    return o;
}

template<typename T>
class myStack {
public:
    int topptr = 0;
    T *arr = nullptr;

    explicit myStack(int n) {
        arr = (T *) malloc(n * sizeof(T));
    }

    myStack() = default;

    [[nodiscard]] T top() const {
        return *(arr + topptr);
    };

    [[nodiscard]] bool empty() const {
        return topptr == 0;
    }

    void push(T x) {
        topptr++;
        *(arr + topptr) = x;
    }

    T pop() {
//        if (topptr == 0) return 0;
//        else
        return *(arr + topptr--);
    }

    friend ostream &operator<<(ostream &o, myStack &s) {
//        for (int i = 1; i <= s.topptr; i++) {
//            o << s.arr[i] << ' ';
//        }
        while (!s.empty())
            o << s.pop();
        return o;
    }
};

myStack<pii> st;

inline bool good(int nx, int ny) {
    return !vis[nx][ny] && nx < 9 && nx > 0 && ny < 9 && ny > 0;
}

int find(int x, int y) {
    int cnt = 0;
    for (int i = 0; i < 8; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (good(nx, ny))
            cnt++;
    }
    return cnt;
}


bool dfs(int x, int y, int cnt) {
    if (cnt == 64) {
        return true;
    }
    pii arr[8];
    int siz = 0;
    for (int i = 0; i < 8; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (good(nx, ny)) {
            arr[siz++] = {find(nx, ny), i};
        }
    }

    for (int i = 0; i < siz; i++) {
        for (int j = i + 1; j < siz; j++) {
            if (arr[i].first > arr[j].first) {
                swap(arr[i], arr[j]);
            }
        }
    }

    for (int i = 0; i < siz; i++) {
        int nx = x + dx[arr[i].second];
        int ny = y + dy[arr[i].second];
        vis[nx][ny] = true;
        if (dfs(nx, ny, cnt + 1)) {
            st.push({nx, ny});
            return true;
        }
        vis[nx][ny] = false;
    }

    return false;
}

int main() {
    st = myStack<pii>(100);
    int x, y;
    cin >> x >> y;
    vis[x][y] = true;
    if (!dfs(x, y, 1)) cout << "NO";
    else {
        st.push({x, y});
        cout << st;
    }
}