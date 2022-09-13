package Ch2;

import java.util.Scanner;

public class P2_21 {
    public static void main(String[] args) {
        System.out.print("Enter investment amount: ");
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();
        System.out.print("Enter annual interest rate in percentage: ");
        double inte = scanner.nextDouble();
        System.out.print("ENter number of years: ");
        int year = scanner.nextInt();
        System.out.print("Future value is $" + amount * Math.pow(1 + inte / 1200, year * 12));
    }
}
