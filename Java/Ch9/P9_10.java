package Ch9;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P9_10 {
    public static void main(String[] args) {
        out.print("Please enter a, b and c: ");
        Scanner scanner = new Scanner(in);
        QuadraticEquation quadraticEquation = new QuadraticEquation(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
        out.println("The discriminant is " + quadraticEquation.getDiscriminant());
        out.println(quadraticEquation.getRoots());
    }
}

class QuadraticEquation {
    private final double a, b, c;

    QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String getRoots() {
        if (getDiscriminant() < 0) return "The equation has no roots.";
        if (getDiscriminant() == 0) return "The only root is " + getRoot1();
        return "The root1 is " + getRoot1() + "\nThe root2 is " + getRoot2();
    }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }

    public double getRoot1() {
        double dis = getDiscriminant();
        if (dis < 0) return 0;
        return (-b + Math.sqrt(dis)) / 2 * a;
    }

    public double getRoot2() {
        double dis = getDiscriminant();
        if (dis < 0) return 0;
        return (-b - Math.sqrt(dis)) / 2 * a;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
