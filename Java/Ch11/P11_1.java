package Ch11;

import java.util.Date;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P11_1 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        out.print("Enter a triangle with three edges, color and is filled: ");
        Triangle triangle = new Triangle(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
        triangle.setColor(scanner.next());
        triangle.setFilled(scanner.nextBoolean());
        out.println("area = " + triangle.getArea());
        out.println("perimeter = " + triangle.getPerimeter());
        out.println("color = " + triangle.getColor());
        out.println("filled = " + triangle.isFilled());
    }
}


class Triangle extends GeometricObject {
    double side1 = 1, side2 = 1, side3 = 1;

    Triangle() {
    }

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    public double getArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public String toString() {
        return "side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }
}


class GeometricObject {
    private String color = "white";
    private boolean filled;
    final private Date dateCreated;

    public GeometricObject() {
        dateCreated = new Date();
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
        this.dateCreated = new Date();
    }

    @Override
    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + color + "and filled: " + filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
