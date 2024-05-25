#include<bits/stdc++.h>

#define IO ios::sync_with_stdio(false); \
            cin.tie(nullptr);           \
            cout.tie(nullptr)
using namespace std;
using pii = pair<int, int>;
using i64 = long long;

int main() {
    IO;
    int n, m;
    cin >> n >> m;
    string str;
    cin >> str;
    while (n--) {
        vector<string> strs(m);
        for (auto &i: strs) cin >> i;
        cout << '(';
        for (int i = 0; i < m; i++) {
            if (str[i] == '1' && strs[i] != "NULL") cout << '\'';
            cout << strs[i];
            if (str[i] == '1' && strs[i] != "NULL") cout << '\'';
            if (i != m - 1)cout << ", ";
        }
        cout << "), \n";
    }
}