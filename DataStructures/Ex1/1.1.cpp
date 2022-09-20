#include <bits/stdc++.h>

using namespace std;

inline int lcm(int a, int b) {
    return a / __gcd(a, b) * b;
}

namespace sux {
    class real {
        int deno = 1, mole = 0;//mu zi
        ;;
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

        double toDouble() const {
            return (double) this->mole / this->deno;
        }

        friend ostream &operator<<(ostream &o, real &r) {
            o << r.mole << '/' << r.deno;
            return o;
        }

        friend istream &operator>>(istream &i, real &r) {
            char ch;
            int m, d;
            i >> m >> ch >> d;
            r = sux::real(m, d);
            return i;
        }

        real(int m, int d) {
            int gcd = __gcd(m, d);
            this->mole = m / gcd;
            this->deno = d / gcd;
        }

        real() : deno(1), mole(0) {}
    };
}


int main() {
    sux::real a, b, ans;
    double x, y;

    function<void(sux::real &)> calc = [](sux::real &r) {
        double x;
        cin >> x;
        int a = (int) x, t = 1;
        while (a != x) {
            x *= 10;
            a = (int) x;
            t *= 10;
        }
        r = sux::real(a, t);
    };
    cout << "����������������: ";
    cout << "�����������f,����������Ϊ������С����";
    string str;
    cin >> str;
    if (str[0] == 'f') cout << "ע�⣺���������ʽΪ ����/��ĸ �������ӷ�ĸ��/������\n";
    cout << "���������һ������";
    if (str[0] != 'f') calc(a);
    else cin >> a;

    cout << "��������ڶ�������";
    if (str[0] != 'f') calc(b);
    else cin >> b;
    cout << "���������㷽ʽ (+,-,*,/)��";
    cin >> str;
    if (str[0] == '+') ans = a + b;
    else if (str[0] == '-') ans = a - b;
    else if (str[0] == '*') ans = a * b;
    else if (str[0] == '/') ans = a / b;
    else {
        cout << "�Ƿ����룡";
        return 0;
    }
    cout << "���ǣ�������ʽ����" << ans << endl;
    cout << "���ǣ�С����ʽ����" << ans.toDouble() << endl;
}
