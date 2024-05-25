#include <iostream>
using namespace std;
// 异常的嵌套
void fc() {
    try {
        throw "help...";
    } catch (int x) {
        cout << " in fc..int handler " << endl;
    }

    try {
        cout << " no error handle..." << endl;
    } catch (char *px) {
        cout << " in fc..char* handler " << endl;
    }
}

void fb() {
    int *q = new int[10];
    try {
        fc();
        cout << " return from fc() " << endl;
    } catch (...) {
        delete[] q;
        cout << "catch in fb " << endl;
        throw;
    }
}

void fa() {
    char *p = new char[10];
    try {
        fb();
        cout << " return from fb() " << endl;
    } catch (...) {
        delete[] p;
        cout << "catch in fa " << endl;
        throw;
    }
}

int main() {
    try {
        fa();
        cout << " return from fa()" << endl;
    } catch (...) {
        cout << " in main " << endl;
    }
    cout << " end " << endl;
    return 0;
}
