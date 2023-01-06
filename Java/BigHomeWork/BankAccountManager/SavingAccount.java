package BigHomeWork.BankAccountManager;

import java.util.Scanner;

import static java.lang.System.out;

class SavingAccount extends Account {

    SavingAccount(int x) {
        super(x);
    }

    @Override
    void withdraw(Scanner scanner) {
        if (balance < 1e-10) {
            out.print("There is no money left in this account");
            return;
        }
        out.print("Please enter the amount you want to deposit: ");
        double money = scanner.nextDouble();
        while (money < 0 || money > balance) {
            if (money < 0) out.print("Please input a positive number: ");
            else out.print("Insufficient fund, please enter a valid amount: ");
            money = scanner.nextDouble();
        }
        balance -= money;
    }
}
