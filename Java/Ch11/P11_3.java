package Ch11;

import java.util.Date;

import static java.lang.System.out;

public class P11_3 {
    public static void main(String[] args) {
        Account a = new Account();
        SavingAccount s = new SavingAccount();
        CheckingAccount c = new CheckingAccount();
        out.println(a);
        out.println(s);
        out.println(c);
    }
}

class CheckingAccount extends Account {
    double extraMoney;

    @Override
    void withDraw(double m) {
        if (this.getBalance() >= m) super.withDraw(m);
        else {
            if (this.extraMoney + this.getBalance() >= m) super.withDraw(m);
        }
    }

}

class SavingAccount extends Account {
    void withDraw(double m) {
        if (this.getBalance() >= m) super.withDraw(m);
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
        return "The balance left is " + this.balance + "\nThe monthly interest is " + this.getMonthlyInterest() + "\nThe time this account created is " + this.dateCreated.toString();

    }
}