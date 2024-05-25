#include <IOSTREAM.H>

#include <CMATH>

class Shape {
   public:
    virtual double get_area() { return 0; };
};

class Rectangle : Shape {
   private:
    double a, b;

   public:
    Rectangle() : a(0), b(0){};
    Rectangle(double a, double b) : a(a), b(b){};
    virtual double get_area() { return a * b; }
};

class Circle : Shape {
   private:
    double r;

   public:
    Circle() : r(0){};
    Circle(int r) : r(r){};
    double get_area() { return r * r * acos(-1); }
};

void main() {
    Rectangle r(2, 5);
    Circle c(2);
    cout << r.get_area() << '\n';
    cout << c.get_area() << '\n';
}