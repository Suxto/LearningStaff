#include <iostream.h>
#include <stdlib.h>
double division1(double x, double y) {
    if (y == 0) {
        cout << " bad data " << endl;
        exit(0);
    } else
        return x / y;
}

double division2(double x, double y) {
    try {
        if (y == 0) throw y;
    } catch (double y) {
        cout << " bad data " << endl;
        exit(0);
    }
    return x / y;
}

double division3(double x, double y) {
    if (y == 0) throw y;
    return x / y;
}

void main() {
    double x = 10, y = 0;
    try {
        cout << division3(x, y) << endl;
    }

    catch (double y) {
        cout << " bad data " << endl;
        exit(0);
    }
}
