#include <iostream.h>

class A {
   public:
    A(int i = 0) : a(i) { cout << " constructionn A\n"; }
    ~A() { cout << " destructing A of " << a << endl; }

   private:
    int a;
};

class B {
   public:
    B(int k) {
        cout << " constructing B" << endl;
        if (k > 3) throw k;
    }
    ~B() { cout << " destructing B" << endl; }

   private:
    A obj[3];
};

void main() {
    try {
        B b(4);
    } catch (int e) {
        cout << "catch an exception of " << e << endl;
    }
}
