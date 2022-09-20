package Ch4;

import java.util.Scanner;

public class P4_1 {
    public static void main(String[] args) {
        System.out.print("Enter the length from the center to a vertex: ");
        final double r = new Scanner(System.in).nextDouble();
        double s = 2 * r * Math.sin(Math.PI / 5);
        double ans = s * s * 5.0 / (4 * Math.tan(Math.PI / 5));
        System.out.print("The area of the pentagon is " + ans);
    }
}
