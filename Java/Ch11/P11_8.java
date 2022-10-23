package Ch11;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

public class P11_8 {
    public static void main(String[] args) {
        Account1 account = new Account1("George", 1122, 1000);
        account.deposit(30);
        account.deposit(40);
        account.deposit(50);
        account.withDraw(5);
        account.withDraw(4);
        account.withDraw(2);
        account.setAnnualInterestRate(1.5);
        out.println(account);
        out.println("The transaction show as below");
        for (Transaction t : account.transactions) {
            out.println(t);
        }
    }
}

class Transaction {
    Date date;
    char type;
    double amount, balance;
    String description;

    public Transaction(char type, double amount, double balance, String description) {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "date " + date +
                "  type=" + type +
                "  amount=" + amount +
                "  balance=" + balance +
                "  description='" + description;
    }
}

class Account1 {
    String name;
    ArrayList<Transaction> transactions = new ArrayList<>();
    private int id = 0;

    private double balance = 0;
    private static double annualInterestRate = 0;
    private final Date dateCreated;

    public Account1(String name, int id, double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        dateCreated = new Date();
    }

    Account1() {
        dateCreated = new Date();
    }

    Account1(int id, double balance) {
        this.id = id;
        this.balance = balance;
        dateCreated = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        Account1.annualInterestRate = annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    double getMonthlyInterestRate() {
        return annualInterestRate / 12;
    }

    double getMonthlyInterest() {
        return balance * getMonthlyInterestRate();
    }

    void withDraw(double m) {
        this.balance -= m;
        transactions.add(new Transaction('W', balance, m, "with draw"));
    }

    void deposit(double m) {
        this.balance += m;
        transactions.add(new Transaction('D', balance, m, "d"));
    }

    @Override
    public String toString() {
        return "The balance left is " + this.balance + "\nThe monthly interest is " + this.getMonthlyInterest() + "\nThe user name is " + this.name;
    }
}