#include <IOSTREAM.H>

#include <CSTRING>
class Vehicle {
   protected:
    char *name;

   public:
    Vehicle(char *n) {
        name = new char[strlen(n)];
        strcpy(name, n);
    }
    virtual void display_with(ostream &o) = 0;
};

class Car : public Vehicle {
   public:
    Car(char *n) : Vehicle(n){};
    void display_with(ostream &o) {
        o << "The name of this car is " << name << '\n';
    }
};
class Truck : public Vehicle {
   public:
    Truck(char *n) : Vehicle(n){};
    void display_with(ostream &o) {
        o << "The name of this truck is " << name << '\n';
    }
};
class Boat : public Vehicle {
   public:
    Boat(char *n) : Vehicle(n){};
    void display_with(ostream &o) {
        o << "The name of this boat is " << name << '\n';
    }
};
void main() {
    char tmp[20];
    cin >> tmp;
    Car c(tmp);
    cin >> tmp;
    Truck t(tmp);
    cin >> tmp;
    Boat b(tmp);
    c.display_with(cout);
    t.display_with(cout);
    b.display_with(cout);
}