#include <iostream>
// 抛出类对象
using namespace std;

class A {
   public:
    A(int x = 0) : a(x) { cout << "constructing A with: " << a << endl; }
    A(const A& r) {
        a = r.a + 1;
        cout << "copy constructing A with: " << a << endl;
    }
    int getx() { return a; }
    ~A() { cout << "destructing A of " << a << endl; }

   private:
    int a;
};

int main() {
    try {
        A* a = new A;  // A a(1);
        throw *a;      // throw a;
        cout << "in try block " << endl;
    } catch (A a) {
        cout << "in catch block, a= " << a.getx() << endl;
    }
    cout << "in main" << endl;
    return 0;
}
