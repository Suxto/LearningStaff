package Ch13;

import static java.lang.System.out;

public class P13_12 {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(1, 2);
        Rectangle r2 = new Rectangle(2, 2);
        Circle c1 = new Circle(1);
        Circle c2 = new Circle(2);
        GeometricObject[] geometricObjects = new GeometricObject[4];
        geometricObjects[0] = r1;
        geometricObjects[1] = r2;
        geometricObjects[2] = c1;
        geometricObjects[3] = c2;
        out.print("The area is " + sumArea(geometricObjects));
    }

    public static double sumArea(GeometricObject[] arr) {
        double sum = 0;
        for (GeometricObject g : arr) {
            sum += g.getArea();
        }
        return sum;
    }
}

class Rectangle extends GeometricObject {
    private double width;
    private double height;

    public Rectangle() {
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Set a new width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set a new height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    /** Return area */ public double getArea() {
        return width * height;
    }

    @Override
    /** Return perimeter */ public double getPerimeter() {
        return 2 * (width + height);
    }
}