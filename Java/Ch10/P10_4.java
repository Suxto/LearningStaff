package Ch10;

import static java.lang.System.out;

public class P10_4 {
    public static void main(String[] args) {
        MyPoint a = new MyPoint();
        out.println(a.distance(10, 30.5));
    }
}


class MyPoint {
    double x = 0, y = 0;

    MyPoint() {
    }

    public double distance(MyPoint m) {
        return distance(this, m);
    }

    public double distance(double x, double y) {
        return distance(this, new MyPoint(x, y));
    }

    public static double distance(MyPoint a, MyPoint b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}