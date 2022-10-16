package Ch10;

import static java.lang.System.out;

public class P10_12 {
    public static void main(String[] args) {
        Triangle2D t1 = new Triangle2D(new MyPoint1(2.5, 2), new MyPoint1(4.2, 3), new MyPoint1(5, 3.5));
        out.println(t1);
        out.println(t1.contains(new Triangle2D(new MyPoint1(2.9, 2), new MyPoint1(4, 1), new MyPoint1(1, 3.4))));
        out.println(t1.overlaps(new Triangle2D(new MyPoint1(2, 5.5), new MyPoint1(4, -3), new MyPoint1(2, 6.5))));
    }
}

class Triangle2D {
    MyPoint1 p1, p2, p3;

    public Triangle2D() {
        this(new MyPoint1(0, 0), new MyPoint1(1, 1), new MyPoint1(2, 5));
    }

    public Triangle2D(MyPoint1 p1, MyPoint1 p2, MyPoint1 p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public double getArea() {
        return 0.5 * (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y - p1.x * p3.y - p2.x * p1.y - p3.x * p2.y);
    }

    public double getPerimeter() {
        return p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
    }

    public boolean contains(MyPoint1 p) {
        return new Triangle2D(p, p1, p2).getArea() + new Triangle2D(p, p2, p3).getArea() + new Triangle2D(p, p1, p3).getArea() <= this.getArea();
    }

    public boolean contains(Triangle2D t) {
        if (!this.contains(t.getP1())) return false;
        if (!this.contains(t.getP2())) return false;
        return this.contains(t.getP3());
    }

    public static boolean judge(MyPoint1 a, MyPoint1 b, MyPoint1 c, MyPoint1 d) {
        double AB_AC = (c.x - a.x) * (b.y - a.y) - (b.x - a.x) * (c.y - a.y);
        double AB_AD = (d.x - a.x) * (b.y - a.y) - (b.x - a.x) * (d.y - a.y);
        double CD_CA = (a.x - c.x) * (d.y - c.y) - (d.x - c.x) * (a.y - c.y);
        double CD_CB = (b.x - c.x) * (d.y - c.y) - (d.x - c.x) * (b.y - c.y);
        return Double.compare(AB_AC * AB_AD, 0) < 0 && Double.compare(CD_CA * CD_CB, 0) < 0;
    }

    @Override
    public String toString() {
        return "Area = " + getArea() + "\nPerimeter = " + getPerimeter();
    }

    public boolean overlaps(Triangle2D t) {
        return judge(this.p1, this.p2, t.p1, t.p2) || judge(this.p1, p2, t.p1, t.p3) || judge(this.p1, this.p2, t.p2, t.p3) || judge(this.p1, this.p3, t.p1, t.p2) || judge(this.p1, p3, t.p1, t.p3) || judge(this.p1, this.p3, t.p2, t.p3) || judge(this.p2, this.p3, t.p1, t.p2) || judge(this.p2, p3, t.p1, t.p3) || judge(this.p2, this.p3, t.p2, t.p3);
    }

    public MyPoint1 getP1() {
        return p1;
    }

    public void setP1(MyPoint1 p1) {
        this.p1 = p1;
    }

    public MyPoint1 getP2() {
        return p2;
    }

    public void setP2(MyPoint1 p2) {
        this.p2 = p2;
    }

    public MyPoint1 getP3() {
        return p3;
    }

    public void setP3(MyPoint1 p3) {
        this.p3 = p3;
    }
}


class MyPoint1 {
    double x = 0, y = 0;

    MyPoint1() {
    }

    public double distance(MyPoint1 m) {
        return distance(this, m);
    }

    public double distance(double x, double y) {
        return distance(this, new MyPoint1(x, y));
    }

    public static double distance(MyPoint1 a, MyPoint1 b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public MyPoint1(double x, double y) {
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