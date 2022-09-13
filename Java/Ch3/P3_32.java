package Ch3;

import java.util.Scanner;

public class P3_32 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter three points for p0, p1 and p2: ");
        double x0 = scanner.nextDouble(), y0 = scanner.nextDouble();
        double x1 = scanner.nextDouble(), y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble(), y2 = scanner.nextDouble();
        double ans = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);

        System.out.print("p2 is one the ");

        if (ans > 0) System.out.print("left side of the line");
        else if (ans == 0) System.out.print("same line");
        else System.out.print("right side of the line");

    }
}
