package Ch9;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P9_11 {
    public static void main(String[] args) {
        out.print("Enter a, b, c, d, e and f: ");
        Scanner scanner = new Scanner(in);
        LinearEquation linearEquation = new LinearEquation(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
        if (linearEquation.isSolvable()) out.printf("X = %f \nY = %f", linearEquation.getX(), linearEquation.getY());
        else out.print("The equation has no solution.");
    }
}

class LinearEquation {
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    LinearEquation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public boolean isSolvable() {
        return a * d - b * c != 0;
    }

    public double getX() {
        return (e * d - b * f) / (a * d - b * c);
    }

    public double getY() {
        return (a * f - e * c) / (a * d - b * c);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (LinearEquation) obj;
        return Double.doubleToLongBits(this.a) == Double.doubleToLongBits(that.a) &&
                Double.doubleToLongBits(this.b) == Double.doubleToLongBits(that.b) &&
                Double.doubleToLongBits(this.c) == Double.doubleToLongBits(that.c) &&
                Double.doubleToLongBits(this.d) == Double.doubleToLongBits(that.d) &&
                Double.doubleToLongBits(this.e) == Double.doubleToLongBits(that.e) &&
                Double.doubleToLongBits(this.f) == Double.doubleToLongBits(that.f);
    }

    @Override
    public String toString() {
        return "LinearEquation[" +
                "a=" + a + ", " +
                "b=" + b + ", " +
                "c=" + c + ", " +
                "d=" + d + ", " +
                "e=" + e + ", " +
                "f=" + f + ']';
    }

    public double a() {
        return a;
    }

    public double b() {
        return b;
    }

    public double c() {
        return c;
    }

    public double d() {
        return d;
    }

    public double e() {
        return e;
    }

    public double f() {
        return f;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d, e, f);
    }


}