package Ch9;

import static java.lang.System.out;

public class P9_1 {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(4, 40);
        Rectangle r2 = new Rectangle(3.5, 35.9);
        out.println(r1.getArea());
        out.println(r1.getPerimeter());
        out.println(r2.getArea());
        out.println(r2.getPerimeter());
    }
}

class Rectangle {
    double width = 1, height = 1;

    Rectangle() {

    }

    Rectangle(double wid, double hei) {
        this.width = wid;
        this.height = hei;
    }

    double getArea() {
        return this.width * this.height;
    }

    double getPerimeter() {
        return (width + height) * 2;
    }

}