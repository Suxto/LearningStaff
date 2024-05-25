#include <iostream.h>
// 重新抛出异常
void divide(double x, double y) {
    cout << " in function divide" << endl;
    try {
        if (y == 0)
            throw y;
        else
            cout << " division = " << x / y << endl;
    } catch (double y) {
        cout << " catch double " << y << " in function divide " << endl;
        throw;
    }
    cout << " end of divide " << endl;
}

void main() {
    cout << " in main " << endl;
    try {
        divide(10, 2);
        divide(20, 0);
    } catch (double y) {
        cout << " catch double " << y << " in function main " << endl;
    }
    cout << " end of main " << endl;
}
