#include <iostream>
using namespace std;

template <typename T>
T& min(T& a, T& b) {
    if (a < b) return a;
    return b;
}

char* min(char* a, char* b) {
    if (strcmp(a, b) < 0) return a;
    return b;
}

void main() {
    int ia = 1, ib = 2;
    double da = 0.1, db = 0.2;
    char *sa = "aa", *sb = "ab";
    cout << min(ia, ib) << '\n';
    cout << min(da, db) << '\n';
    cout << min(sa, sb) << '\n';
}