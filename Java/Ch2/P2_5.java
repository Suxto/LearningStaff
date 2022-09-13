package Ch2;

import java.util.Scanner;

public class P2_5 {
    public static void main(String[] args) {
        System.out.print("Enter the subtotal and a gratuity rate: ");
        Scanner scanner = new Scanner(System.in);
        final double TOT = scanner.nextDouble();
        final double RATE = scanner.nextDouble() / 100.0;
        System.out.println("The gratuity is $" + TOT * RATE + " and total is $" + TOT * (1d + RATE));
    }
}
