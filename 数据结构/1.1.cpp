#include <bits/stdc++.h>

using namespace std;

inline int lcm(int a, int b) {
    return a / __gcd(a, b) * b;
}

namespace sux {
    struct real {
        int deno = 1, mole = 0;//mu zi
        real(int m, int d) : deno(d), mole(m) {};

        real() : deno(1), mole(0) {};
    public:
        real operator+(real &r) const {
            int nd = lcm(this->deno, r.deno);//mu
            int nm = this->mole * (nd / this->deno) + r.mole * (nd / r.deno);
            int sign = nm > 0 ? 1 : -1;
            nm *= sign;
            int div = __gcd(nm, nd);
            return {sign * nm / div, nd / div};
        }

        real operator-(real &r) const {
            int nd = lcm(this->deno, r.deno);//mu
            int nm = this->mole * (nd / this->deno) - r.mole * (nd / r.deno);
            int sign = nm > 0 ? 1 : -1;
            nm *= sign;
            int div = __gcd(nm, nd);
            return {sign * nm / div, nd / div};
        }

        real operator*(real &r) const {
            int nm = this->mole * r.mole;
            int nd = this->deno * r.deno;
            int sign = nd * nm > 0 ? 1 : -1;
            nm = abs(nm), nd = abs(nd);
            int div = __gcd(nm, nd);
            return {sign * nm / div, nd / div};
        }

        real operator/(real &r) const {
            int nm = this->mole * r.deno;
            int nd = this->deno * r.mole;
            int sign = nd * nm > 0 ? 1 : -1;
            nm = abs(nm), nd = abs(nd);
            int div = __gcd(nm, nd);
            return {sign * nm / div, nd / div};
        }

        friend ostream &operator<<(ostream &o, real &r) {
            o << r.mole << '/' << r.deno;
            return o;
        }

        friend istream &operator>>(istream &i, real &r) {
            char ch;
            i >> r.mole >> ch >> r.deno;
            return i;
        }
    };
}


int main() {
    sux::real a, b;
    cin >> a >> b;
    sux::real c = a / b;
    cout << c;
}
