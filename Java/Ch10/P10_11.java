package Ch10;

import static java.lang.System.out;

public class P10_11 {
    public static void main(String[] args) {
        Circle2D c1 = new Circle2D(2, 2, 5.5);
        out.println(c1);
        out.println(c1.contains(3, 3));
        out.println(c1.contains(new Circle2D(4, 5, 10.5)));
        out.println(c1.overlaps(new Circle2D(3, 5, 2.3)));
    }
}

class Circle2D {
    private final double x, y;//center
    private final double radius;

    public Circle2D() {
        this(0, 0, 1);
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return Math.PI * radius * 2;
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public boolean contains(double x, double y) {
        return distance(x, y, this.x, this.y) < radius;
    }

    public boolean contains(Circle2D circle) {
        return distance(this.x, this.y, circle.x, circle.y) + circle.radius < this.radius;
    }

    public boolean overlaps(Circle2D circle) {
        return distance(this.x, this.y, circle.x, circle.y) < this.radius + circle.radius;
    }

    public Circle2D(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "The area is " + getArea() +
                "\nThe perimeter is " + getPerimeter();
    }
}