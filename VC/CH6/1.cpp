#include <iostream>
using namespace std;

class Complex {
   private:
    double real, imag;

   public:
    Complex() : real(0), imag(0){};
    friend istream &operator>>(istream &in, Complex &c) {
        in >> c.real >> c.imag;
        return in;
    }
    friend ostream &operator<<(ostream &o, Complex &c) {
        o << c.real;
        if (c.imag > 0)
            o << " + " << c.imag << 'i';
        else if (c.imag < 0)
            o << " - " << -c.imag << 'i';
        return o;
    }
};

void main() {
    Complex a;
    cin >> a;
    cout << a;
}