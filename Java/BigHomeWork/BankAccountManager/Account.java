package BigHomeWork.BankAccountManager;

import java.util.Scanner;

import static java.lang.System.out;

abstract class Account {
    protected double balance = 0;
    int id;

    Account(int x) {
        id = x;
    }

    abstract void withdraw(Scanner scanner);

    public void deposit(Scanner scanner) {
        out.print("Please enter the amount you want to deposit: ");
        double money = scanner.nextDouble();
        while (money < 0) {
            out.print("Please enter a valid number: ");
            money = scanner.nextDouble();
        }
        if (money > 1e4) {
            out.print("The amount you deposit is above 10,000 dollars, please reach bank manager for help!\n");
            return;
        }
        balance += money;
    }
}
