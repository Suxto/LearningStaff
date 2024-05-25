#include <iostream>
using namespace std;
int main() {
    try {
        throw "hello";
    } catch (int x) {
        cout << "catched " << x;
    }
    return 0;
}