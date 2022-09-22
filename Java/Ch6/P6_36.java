package Ch6;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

class P6_36 {

    static double calc(int n, double s) {
        return n * s * s / (4 * Math.tan(Math.PI / n));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.print("Enter the number of sides: ");
        int n = scanner.nextInt();
        out.print("Enter the side: ");
        out.print("The area of the polygon is " + calc(n, scanner.nextDouble()));
    }

}