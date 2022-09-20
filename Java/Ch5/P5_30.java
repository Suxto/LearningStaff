package Ch5;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P5_30 {
    static boolean isLeap(int n) {
        return n % 4 == 0 && (n % 400 == 0 || n % 100 != 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.print("Enter the amount of your account: ");
        double amount = scanner.nextDouble();
        out.print("Enter annual interest rate: ");
        double rate = scanner.nextDouble() / 1200.0;
        out.print("Enter the month that you save: ");
        int mon = scanner.nextInt();

        double sum = 0;
        for (int i = 1; i <= mon; i++) {
            sum = (sum + amount) * (1 + rate);
        }
        out.printf("%.3f", sum);
    }
}
