#include <iostream>
using namespace std;

template <typename T>
void sort(T* arr, int len) {
    for (int i = 0; i < len; i++)
        for (int j = i + 1; j < len; j++)
            if (arr[i] > arr[j]) {
                // T tmp = arr[i];
                // arr[i] = arr[j];
                // arr[j] = tmp;
                swap(arr[i], arr[j]);
            }
}

template <typename T>
void print_to(ostream& o, T* arr, int len) {
    for (int i = 0; i < len; i++) o << arr[i] << ' ';
}

void main() {
    int irr[] = {5, 4, 3, 2, 1};
    double drr[] = {1.5, 1.2, 1.1, 0.5, 0};
    sort(irr, 5);
    sort(drr, 5);
    print_to(cout, irr, 5);
    cout << '\n';
    print_to(cout, drr, 5);
}