package Ch5;

import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.System.*;

public class P5_22 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.print("Loan Amount: ");
        double amount = scanner.nextDouble();

        out.print("Number of Years: ");
        int year = scanner.nextInt();

        out.print("Annual Interest Rate: ");
        double rate = scanner.nextDouble() / 100;
        out.print("\n");

        double monthlyPayment = (amount * rate / 12) / (1 - 1 / pow(1 + rate / 12, year * 12));
        double totalPayment = monthlyPayment * year * 12;
        out.printf("Monthly Payment: %.2f\n", monthlyPayment);
        out.printf("Total Payment: %.2f\n", totalPayment);

        out.print("Payment# Interest\tPrincipal\tBalance\n");

        double interest = 0.0, principal = 0.0;
        for (int i = 1; i <= year * 12; i++) {
            interest = rate / 12 * amount;
            principal = monthlyPayment - interest;
            amount -= principal;
            if (i == year * 12)
                principal += amount;
            out.print(i + "\t\t ");
            out.printf("%.2f\t\t%.2f\t\t%.2f\n", interest, principal, amount);
        }
    }
}
