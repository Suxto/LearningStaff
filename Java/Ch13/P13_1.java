package Ch13;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P13_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.print("Enter the 3 edges of the triangle: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        out.print("Enter the color of the triangle: ");
        String str = scanner.next();
        out.print("Is the triangle filled? (Y/N) ");
        String filled = scanner.next();
        Triangle triangle = new Triangle(a, b, c, str, filled.charAt(0) == 'Y');
        out.println("The area of the triangle is " + triangle.getArea());
        out.println("The perimeter of the triangle is " + triangle.getPerimeter());
        out.print(triangle);
    }
}

abstract class GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    protected GeometricObject() {
        dateCreated = new java.util.Date();
    }

    protected GeometricObject(String color, boolean filled) {
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + color + " and filled: " + filled;
    }

    public abstract double getArea();

    public abstract double getPerimeter();

}

class Triangle extends GeometricObject {
    double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle(double a, double b, double c, String color, boolean filled) {
        super(color, filled);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getArea() {
        return 0.25 * Math.sqrt(4d * a * a * b * b - (a * a + b * b - c * c) * (a * a + b * b - c * c));
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }
}