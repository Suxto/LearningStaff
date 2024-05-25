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
    // Complex operator=(Complex &that) { return Complex(that[0], that[1]); }
    friend Complex operator+(Complex &ths, Complex &that) {
        return Complex(ths[0] + that[0], ths[1] + that[1]);
    }
    friend Complex operator-(Complex &ths, Complex &that) {
        return Complex(ths[0] - that[0], ths[1] - that[1]);
    }
    friend Complex operator*(Complex &ths, Complex &that) {
        double a = ths[0], b = ths[1], c = that[0], d = that[1];
        return Complex(a * c - b * d, b * c + a * d);
    }
    friend Complex operator/(Complex &ths, Complex &that) {
        double a = ths[0], b = ths[1], c = that[0], d = that[1];
        double frac = c * c + d * d;
        return Complex((a * c + b * d) / frac, (b * c + a * d) / frac);
    }
    friend bool operator==(Complex &ths, Complex &that) {
        return ths[0] == that[0] && ths[1] == that[1];
    }
    friend bool operator!=(Complex &ths, Complex &that) {
        return !(ths == that);
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
    cout << (x == y) << ' ' << (x != y);
}
