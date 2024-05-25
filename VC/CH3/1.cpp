#include <iostream.h>

class Complex {
   private:
    double real, imag;

   public:
    Complex(double real, double imag) : real(real), imag(imag) {}
    Complex(Complex &that) : real(that.real), imag(that.imag) {}
    void show(ostream &o) { o << real << '+' << imag << 'i'; }
};

void main() {
    double a, b;
    cin >> a >> b;
    Complex aa(a, b);
    Complex bb(aa);
    aa.show(cout);
    cout << '\n';
    bb.show(cout);
}