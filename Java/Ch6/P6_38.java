package Ch6;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

class P6_38 {
    public static boolean leftOfTheLine(double x0, double y0, double x1, double y1, double x2, double y2) {
        return calc(x0, y0, x1, y1, x2, y2) > 0;
    }

    public static boolean rightOfTheLine(double x0, double y0, double x1, double y1, double x2, double y2) {
        return calc(x0, y0, x1, y1, x2, y2) < 0;
    }

    public static boolean onTheSameLine(double x0, double y0, double x1, double y1, double x2, double y2) {
        return calc(x0, y0, x1, y1, x2, y2) == 0;
    }

    public static boolean onTheLineSegment(double x0, double y0, double x1, double y1, double x2, double y2) {
        double dx = (x2 - x0) * (x2 - x1);
        double dy = (y2 - y0) * (y2 - y1);
        return dy < 0 && dx < 0;
    }

    public static double calc(double x0, double y0, double x1, double y1, double x2, double y2) {
        return (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter three points for p0, p1, and p2: ");
        double x0 = scanner.nextDouble(), y0 = scanner.nextDouble();
        double x1 = scanner.nextDouble(), y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble(), y2 = scanner.nextDouble();
        double ans = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);

        System.out.printf("(%.1f, %.1f) is one the ", x2, y2);

        if (leftOfTheLine(x0, y0, x1, y1, x2, y2))
            System.out.printf("left side of the line from (%.1f, %.1f) to (%.1f, %.1f)", x0, y0, x1, y1);
        else if (rightOfTheLine(x0, y0, x1, y1, x2, y2))
            System.out.printf("right side of the line from (%.1f, %.1f) to (%.1f, %.1f)", x0, y0, x1, y1);
        else if (onTheLineSegment(x0, y0, x1, y1, x2, y2))
            System.out.printf("the line segment from (%.1f, %.1f) to (%.1f, %.1f)", x0, y0, x1, y1);
        else
            System.out.printf("the same line from (%.1f, %.1f) to (%.1f, %.1f)", x0, y0, x1, y1);

    }
}