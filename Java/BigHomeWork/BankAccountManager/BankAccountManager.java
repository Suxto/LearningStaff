package BigHomeWork.BankAccountManager;


import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

public class BankAccountManager {
    static ArrayList<Account> list = new ArrayList<>();
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        out.print("----------Welcome to Bank Account Manager!-------------\n");
        out.print("Please input your PIN of 4 digit(a single 0 for exit): ");
        int input = scanner.nextInt();
        if (input == 0) end();
        while (input < 999 || input > 9999) {
            out.print("Pleas enter a four-digit number as your PIN: ");
            input = scanner.nextInt();
        }
        if (!exist(input)) {
            out.print("There is no such an PIN in our database!\n");
            out.print("Do you want to create a new account with your PIN now?(y/n) ");
            char op = scanner.next().charAt(0);
            while (op != 'y' && op != 'n') {
                out.print("Please enter one of 'y' or 'n': ");
                op = scanner.next().charAt(0);
            }
            if (op == 'n') begin();
            else createAccount(input);
        }
        Account account = getAccount(input);
        assert account != null;
        String type = account.getClass().getName();
        type = type.substring(type.lastIndexOf('.') + 1);
        out.println("Your account type is " + type);
        while (true) {
            out.println("The balance remain is " + account.balance);
            out.print("Please select a service: \n");
            out.print("1.Withdraw\n2.Deposit\n");
            out.print("Enter 1 or 2, enter 0 for exit: ");
            input = scanner.nextInt();
            while (input != 1 && input != 2 && input != 0) {
                out.print("Please input 1, 2 or 0: ");
                input = scanner.nextInt();
            }
            if (input == 1) account.withdraw(scanner);
            else if (input == 2) account.deposit(scanner);
            else {
                begin();
                break;
            }
        }
    }

    private static Account getAccount(int input) {
        for (Account account : list) {
            if (account.id == input) return account;
        }
        return null;
    }

    static void createAccount(int x) {
        out.print("Which a account do you want to create?\n");
        out.print("1.checking account\n");
        out.print("2.saving account\n");
        out.print("Enter 1 or 2 to select, 0 for exit: ");
        int input = scanner.nextInt();
        while (input != 1 && input != 2 && input != 0) {
            out.print("Please input 1 or 2, and 0 for exit: ");
            input = scanner.nextInt();
        }
        if (input == 0) end();
        else if (input == 1) {
            list.add(new CheckingAccount(x));
        } else {
            list.add(new SavingAccount(x));
        }
    }

    static void end() {
        out.print("Thanks for using this program.");
        exit(0);
    }

    static void begin() {
        main(new String[]{});
    }

    static boolean exist(int x) {
        for (Account account : list) {
            if (account.id == x) return true;
        }
        return false;
    }
}

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