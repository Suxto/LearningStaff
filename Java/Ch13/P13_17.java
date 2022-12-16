package Ch13;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P13_17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.print("Enter the first complex number: ");
        Complex complex1 = new Complex(scanner.nextDouble(), scanner.nextDouble());
        out.print("Enter the second complex number: ");
        Complex complex2 = new Complex(scanner.nextDouble(), scanner.nextDouble());
        out.println(complex1 + " + " + complex2 + " = " + complex1.add(complex2));
        out.println(complex1 + " - " + complex2 + " = " + complex1.subtract(complex2));
        out.println(complex1 + " * " + complex2 + " = " + complex1.multiply(complex2));
        out.println(complex1 + " / " + complex2 + " = " + complex1.divide(complex2));
        out.print("|" + complex1 + " = " + complex1.abs());
    }
}

class Complex implements Comparable<Complex>, Cloneable {
    private final double a, b;

    public Complex add(Complex c) {
        return new Complex(this.a + c.a, this.b + c.b);
    }

    public Complex subtract(Complex c) {
        return new Complex(this.a - c.a, this.b - c.b);
    }

    public Complex multiply(Complex c) {
        return new Complex(this.a * c.a - this.b * c.b, this.b * c.a + this.a * c.b);
    }

    public Complex divide(Complex c) {
        double t1 = this.a * c.a + this.b * c.b;
        double t2 = this.b * c.a - this.a * c.b;
        double t3 = c.a * c.a + c.b * c.b;
        return new Complex(t1 / t3, t2 / t3);
    }

    public double abs() {
        return Math.sqrt(a * a + b * b);
    }

    public String toString() {
        return "(" + a + (b >= 0 ? " + " : " - ") + Math.abs(b) + "i)";
    }

    public Complex(double a) {
        this(a, 0);
    }

    public double getRealPart() {
        return a;
    }

    public double getImaginaryPart() {
        return b;
    }

    public Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Complex o) {
        return Double.compare(this.abs(), o.abs());
    }

    @Override
    public Complex clone() {
        try {
            return (Complex) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
