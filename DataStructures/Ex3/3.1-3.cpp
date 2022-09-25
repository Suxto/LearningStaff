#include<bits/stdc++.h>

#define MAXN 100000
#define chr str[i]
using namespace std;

template<typename T>
class mystack {
public:
    int topptr = 0;
    T *arr = nullptr;

    explicit mystack(int n) {
        arr = (T *) malloc(n * sizeof(T));
    }

    mystack() = default;

    [[nodiscard]] T top() const {
        return *(arr + topptr);
    };

    [[nodiscard]] bool empty() const {
        return topptr == 0;
    }

    void push(int x) {
        topptr++;
        *(arr + topptr) = x;
    }

    T pop() {
        if (topptr == 0) return 0;
        else return *(arr + topptr--);
    }

    friend ostream &operator<<(ostream &o, mystack &s) {
        for (int i = 1; i <= s.topptr; i++) {
            o << s.arr[i] << ' ';
        }
        return o;
    }
};

int operOrder(char ch) {
    if (ch == '*' || ch == '/') return 2;
    if (ch == '+' || ch == '-') return 1;
    return 0;
}

bool isOper(char ch) {
    if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')') {
        return true;
    }
    return false;
}


int main() {
    auto numStack = mystack<int>(1000);
    auto operStack = mystack<char>(1000);
    string str;
    cin >> str;
    int i = 0;

    auto calc = [](int &a, char &op, int &b) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        if (op == '*') return a * b;
        if (op == '/') return a / b;
        return 0;
    };

    while (i < str.length()) {
//        char chr = str[i];
        if (isOper(chr)) {
            if (operStack.empty()) {
                operStack.push(chr);
            } else {
                if (chr == ')') {
                    while (operStack.top() != '(') {
                        int a = numStack.pop();
                        int b = numStack.pop();
                        char op = operStack.pop();
                        numStack.push(calc(a, op, b));
                    }
                    operStack.pop();
                } else if (chr == '(' || operOrder(operStack.top()) < operOrder(chr)) {
                    operStack.push(chr);
                } else {
                    int a = numStack.pop();
                    int b = numStack.pop();
                    char op = operStack.pop();
                    numStack.push(calc(a, op, b));
                    operStack.push(chr);
                }
            }
            i++;
        } else {
            int x = 0;
            while (i < str.length() && !isOper(chr)) {
                x = x * 10 + chr - '0';
                i++;
            }
            numStack.push(x);
        }
//        cout << "i= " << i << " :" << endl;
//        cout << "Num Stack: " << numStack << endl;
//        cout << "Operator stack: " << operStack << endl;
    }

    while (!operStack.empty()) {
        int a = numStack.pop();
        int b = numStack.pop();
        char op = operStack.pop();
        numStack.push(calc(a, op, b));
    }

    cout << numStack.top();

}