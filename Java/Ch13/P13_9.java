package Ch13;

import java.util.Date;
import java.util.Objects;

public class P13_9 {
}

class Circle extends GeometricObject implements Comparable<Circle> {
    private double radius;
    Date dateCreated;

    public Circle() {
        dateCreated = new Date();
    }

    public Circle(double radius) {
        this();
        this.radius = radius;
    }

    /**
     * Return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Set a new radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return radius * radius * Math.PI;
    }

    /**
     * Return diameter
     */
    public double getDiameter() {
        return 2 * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    /* Print the circle info */
    public void printCircle() {
        System.out.println("The circle is created " + getDateCreated() +
                " and the radius is " + radius);
    }

    private Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int compareTo(Circle o) {
        if (this.equals(o)) return 0;
        return this.radius < o.radius ? 1 : -1;
    }
}

