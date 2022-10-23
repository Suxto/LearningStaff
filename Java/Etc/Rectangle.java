package Etc;

public class Rectangle {
    double width = 1, height = 1;

    public double getWidth() {
        return width;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle() {
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }
}

class TestRectangle {

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();
        System.out.printf("width = %.2f\theight = %.2f\tarea = %.2f\tperimeter = %.2f\n", r1.getWidth(), r1.getHeight(), r1.getArea(), r1.getPerimeter());

        Rectangle r2 = new Rectangle(3.5, 35.9);
        System.out.printf("width = %.2f\theight = %.2f\tarea = %.2f\tperimeter = %.2f\n", r2.getWidth(), r2.getHeight(), r2.getArea(), r2.getPerimeter());

        r1.setWidth(4.87);
        r1.setHeight(12.5);
        System.out.printf("width = %.2f\theight = %.2f\tarea = %.2f\tperimeter = %.2f\n", r1.getWidth(), r1.getHeight(), r1.getArea(), r1.getPerimeter());

    }
}

