#include<bits/stdc++.h>

using namespace std;
default_random_engine e{random_device{}()};
uniform_int_distribution<int> rd_l(1, 17);
uniform_int_distribution<char> rd_c('a', 'z');

int main() {
    fstream f = fstream("stu.txt", ios::out | ios::trunc);
    int t;
    cin >> t;
    f << t << endl;
    while (t--) {
        string str;
        for (int i = 0; i < rd_l(e); i++) str.push_back(rd_c(e));
        f << str << ' ' << ((str[0] & 1) ? "male" : "female") << ' ' << 18 + rd_l(e) % 4 << endl;
    }
}