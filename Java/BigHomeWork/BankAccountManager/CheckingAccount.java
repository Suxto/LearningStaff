package BigHomeWork.BankAccountManager;

import java.util.Scanner;

import static java.lang.System.out;

class CheckingAccount extends Account {

    CheckingAccount(int x) {
        super(x);
    }

    @Override
    void withdraw(Scanner scanner) {
        if (balance < 1e-10) {
            out.print("The balance of this account is " + balance);
            out.print("\nPlease make sure your balance is positive before withdraw\n");
            return;
        }
        out.print("Your account is Checking Account, you can overdraft $100.\n");
        out.print("Note: you would be charge $10 once overdraft!\n");
        out.print("Please enter the amount you want to deposit: ");
        double money = scanner.nextDouble();
        while (money < 0 || money - balance > 90) {
            if (money < 0) out.print("Please input a positive number: ");
            else out.print("Insufficient fund, you can`t overdraw over $100(without charge): ");
            money = scanner.nextDouble();
        }
        if (balance - money < 1e-10) {
            out.print("You are overdrawing and $10 is going to be charged.\n");
            out.print("Are you sure(y/n): ");
            char op = scanner.next().charAt(0);
            while (op != 'y' && op != 'n') {
                out.print("Please enter 'y' or 'n'\n");
                op = scanner.next().charAt(0);
            }
            if (op == 'n') return;
            balance -= 10;
        }
        balance -= money;
    }
}
