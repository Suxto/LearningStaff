#include <IOSTREAM.H>

class Complex {
   private:
    double real, imag;

   public:
    Complex(double r, double i) : real(r), imag(i) {}
    double operator[](int i) {
        if (i == 0) return real;
        return imag;
    }
    Complex operator=(Complex &that) { return Complex(that[0], that[1]); }
    Complex operator+(Complex &that) {
        return Complex(real + that[0], imag + that[1]);
    }
    Complex operator-(Complex &that) {
        return Complex(real - that[0], imag - that[1]);
    }
    Complex operator*(Complex &that) {
        double a = real, b = imag, c = that[0], d = that[1];
        return Complex(a * c - b * d, b * c + a * d);
    }
    Complex operator/(Complex &that) {
        double a = real, b = imag, c = that[0], d = that[1];
        double frac = c * c + d * d;
        return Complex((a * c + b * d) / frac, (b * c + a * d) / frac);
    }
    friend ostream &operator<<(ostream &o, Complex &c) {
        o << c[0];
        if (c[1] > 0)
            o << " + " << c[1];
        else if (c[1] < 0)
            o << " - " << -c[1];
        o << '\n';
        return o;
    }
};
void main() {
    double a, b, c, d;
    cin >> a >> b >> c >> d;
    Complex x(a, b), y(c, d);
    cout << x + y << x - y << x * y << x / y;
}
