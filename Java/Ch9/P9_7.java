package Ch9;

import java.util.Date;

import static java.lang.System.out;

public class P9_7 {

    public static void main(String[] args) {
        Account account = new Account(1122, 20000);
        account.setAnnualInterestRate(4.5 * 1e-2);
        account.withDraw(2500);
        account.deposit(3000);
        out.print(account);
    }
}

class Account {
    private int id = 0;
    private double balance = 0;
    private static double annualInterestRate = 0;
    private final Date dateCreated;

    Account() {
        dateCreated = new Date();
    }

    Account(int id, double balance) {
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
        Account.annualInterestRate = annualInterestRate;
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
    }

    void deposit(double m) {
        this.balance += m;
    }

    @Override
    public String toString() {
        return "The balance left is " + this.balance +
                "\nThe monthly interest is " + this.getMonthlyInterest() +
                "\nThe time this account created is " + this.dateCreated.toString();

    }
}