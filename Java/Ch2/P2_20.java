package Ch2;

import java.util.Scanner;

public class P2_20 {
    public static void main(String[] args) {
        System.out.print("Enter balance and interest rate: ");
        Scanner scanner = new Scanner(System.in);
        double ans = scanner.nextDouble() * scanner.nextDouble() / 1200;
        System.out.print("The interest is " + ans);
    }
}
