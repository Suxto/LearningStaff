package Ch3;

import java.util.Scanner;

public class P3_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a, b, c: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double delta = b * b - 4 * a * c;
        double ans = -b / (2 * a);
        if (delta > 0) {
            double plus = Math.sqrt(delta) / (2 * a);
            System.out.printf("The equation has two roots %f and %f", ans + plus, ans - plus);
        } else if (delta < 0) {
            System.out.print("The equation has no real roots");
        } else {
            System.out.print("The equation has one root " + ans);
        }

    }
}
