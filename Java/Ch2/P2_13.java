package Ch2;

import java.util.Scanner;

public class P2_13 {
    public static void main(String[] args) {
        System.out.print("Enter the monthly saving amount: ");
        final double ms = new Scanner(System.in).nextDouble();
        final double RATE = 0.00417;
        double sum = 0.0;
        for (int i = 0; i < 6; i++) {
            sum = (sum + ms) * (1 + RATE);
        }
        System.out.println("After the sixth month, the account value is $" + sum);
    }
}
