package Ch9;

import static java.lang.System.out;

public class P9_9 {
    public static void main(String[] args) {
        RegularPolygon regularPolygon1 = new RegularPolygon();
        RegularPolygon regularPolygon2 = new RegularPolygon(6, 4);
        RegularPolygon regularPolygon3 = new RegularPolygon(10, 4, 5.6, 7.8);
        out.println("Polygon1:\n" + regularPolygon1);
        out.println("Polygon2:\n" + regularPolygon2);
        out.println("Polygon3:\n" + regularPolygon3);
    }
}

class RegularPolygon {
    private int n = 3;
    private double side = 1;
    private double x = 0;
    private double y = 0;

    RegularPolygon() {
    }

    RegularPolygon(int n, double side) {
        this.n = n;
        this.side = side;
    }

    RegularPolygon(int n, double side, double x, double y) {
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Perimeter = " + this.getPerimeter() +
                "\nArea = " + this.getArea();
    }

    double getPerimeter() {
        return this.n * this.side;
    }

    double getArea() {
        return this.n * this.side * this.side / (4 * Math.tan(Math.PI / n));
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}