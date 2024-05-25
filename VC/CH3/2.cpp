#include <IOSTREAM.H>
class Cat {
   private:
    static int counter;
    const int number;
    char *name;

   public:
    static int howMany() { return counter; }
    Cat(char *n) : name(n), number(++counter) {}
    void show(ostream &os) {
        os << "Cat No." << counter << ' ' << name << '\n';
    }
};
int Cat::counter = 0;

void main() {
    Cat a("hello");
    cout << Cat::howMany() << '\n';
    a.show(cout);
    Cat b("hi");
    cout << Cat::howMany() << '\n';
    b.show(cout);
}