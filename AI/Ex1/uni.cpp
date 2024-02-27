#include<iostream>
#include<vector>
#include<algorithm>

#define FAIL {{"", "", true}}
using namespace std;

struct rep {
    string to, from;
    bool fail = false;

    bool operator==(rep &that) const {
        return this->to == that.to && this->from == that.from;
    }

    bool operator<(const rep &that) const {
        return this->to < that.to;
    }
};

ostream &operator<<(ostream &o, rep &r) {
    o << r.to << '/' << r.from;
    return o;
}

struct list {
    string elem = {};
    vector<list> sublist = {};
    enum {
        ELEM, LIST
    } type = {};


    bool contains(string &str) const {
        if (type == ELEM) {
            return elem == str;
        } else {
            bool res = false;
            for (auto &e: sublist) {
                if (e.type == ELEM)
                    res = res | (str == e.elem);
            }
            return res;
        }
    }

    string to_string() {
        string s;
        if (this->type == list::LIST) {
            s.push_back('(');
            for (auto &ll: this->sublist) s.append(ll.to_string());
            s.push_back(')');
        } else {
            s.append(this->elem + ' ');
        }
        return s;
    }

    void apply(vector<rep> const &r) {
        if (type == ELEM) {
            for (auto &rep: r) {
                if (elem == rep.from) {
                    elem = rep.to;
                    break;
                }
            }
        } else {
            for (auto &l: sublist) {
                l.apply(r);
            }
        }
    }

};

ostream &operator<<(ostream &o, list const &l) {
    if (l.type == list::LIST) {
        o << '(';
        for (auto &ll: l.sublist) o << ll;
        o << ')';
    } else {
        o << l.elem << ' ';
    }
    return o;
}

ostream &operator<<(ostream &o, vector<rep> &vec) {
    o << '{';
    if (!vec.empty()) {
        for (int i = 0; i < vec.size() - 1; i++) {
            o << vec[i] << ',';
        }
        o << vec.back() << '}';
    } else o << '}';
    return o;
}

list conv(const string &input, int &pos) {
    list result = {"", {}, list::LIST};
    while (pos < input.size()) {
        if (input[pos] == '(') {
            ++pos;
            result.sublist.push_back(conv(input, pos));
        } else if (input[pos] == ')') {
            ++pos;
            break;
        } else if (input[pos] == ' ') {
            ++pos;
        } else {
            string elem;
            while (pos < input.size() && input[pos] != '(' && input[pos] != ')' && input[pos] != ' ') {
                elem += input[pos];
                ++pos;
            }
            result.sublist.emplace_back(list{elem, {}, list::ELEM});
        }
    }
    return result;
}

vector<rep> unify(list e1, list e2) {
    if (e1.type == list::LIST && e2.type == list::LIST && e1.sublist.empty() && e2.sublist.empty()) {
        return {};
    }
    if (e1.type == list::ELEM && e2.type == list::ELEM && e1.elem == e2.elem) return {};
    if (e1.type == list::ELEM) {
        if (e2.contains(e1.elem)) {
            return FAIL;
        } else {
            return {{e2.to_string(), e1.to_string(), false}};
        }
    }
    if (e2.type == list::ELEM) {
        if (e1.contains(e2.elem)) {
            return FAIL;
        } else {
            return {{e1.to_string(), e2.to_string(), false}};
        }
    }
    //both list
    if (e1.sublist.empty() || e2.sublist.empty()) return FAIL;
    list he1 = e1.sublist.front();
    list he2 = e2.sublist.front();
    auto subs1 = unify(he1, he2);
    if (subs1.size() == 1 && subs1.front().fail) return FAIL;
//    list te1 = e1, te2 = e2;
//    cout << e1 << '\n' << e2 << '\n'<<"-\n";
    e1.sublist.erase(e1.sublist.begin());
    e2.sublist.erase(e2.sublist.begin());
    e1.apply(subs1);
    e2.apply(subs1);
//    cout << e1 << '\n' << e2 << '\n'<<"--\n";
    auto subs2 = unify(e1, e2);
    if (subs2.size() == 1 && subs2.front().fail) return FAIL;
//    cout << subs1 << '\n' << subs2 << "\n--------------\n";
    subs1.insert(subs1.end(), subs2.begin(), subs2.end());
    return subs1;
}


int main() {
    string s1, s2;
    getline(cin, s1);
    getline(cin, s2);
    int pos = 0;
    list e1 = conv(s1, pos);
    pos = 0;
    list e2 = conv(s2, pos);
    cout << e1 << '\n' << e2 << '\n';
    auto ans = unify(e1, e2);
    sort(ans.begin(), ans.end());
    ans.erase(unique(ans.begin(), ans.end()), ans.end());
    cout << ans;
}
/*
foo x a (goo y)
foo w a (goo jack)

parents X (father X)(mother bill)
parents bill (father bill) Y
*/