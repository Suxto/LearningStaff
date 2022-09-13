package Ch2;

import java.util.Scanner;

public class P2_15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter x1 and y1: ");
        double x1 = scanner.nextDouble(), y1 = scanner.nextDouble();
        System.out.print("Enter x2 and y2: ");
        double dx = x1 - scanner.nextDouble(), dy = y1 - scanner.nextDouble();
        System.out.println("The distance between the two points is " + Math.sqrt(dx * dx + dy * dy));
    }
}
