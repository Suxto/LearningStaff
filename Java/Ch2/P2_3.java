package Ch2;

import java.util.Scanner;

public class P2_3 {
    public static void main(String[] args) {
        System.out.print("Enter a value for feet: ");
        Scanner scanner = new Scanner(System.in);
        final double FEET = scanner.nextDouble();
        System.out.println(FEET + " feet is " + FEET * 0.305 + " meters");
    }
}
