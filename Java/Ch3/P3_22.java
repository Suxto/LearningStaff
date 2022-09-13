package Ch3;

import java.util.Scanner;

public class P3_22 {
    public static void main(String[] args) {
        System.out.print("Enter a point (x,y): ");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        System.out.printf("Point (%d, %d) is ", x, y);
        if (Math.sqrt(x * x + y * y) <= 10.0) System.out.print("in the circle");
        else System.out.print("not in the circle");
    }
}
