package Ch3;

import java.util.Scanner;

public class P3_7 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an amount in double, for example 11.56: ");
        double amount = input.nextDouble();

        int remainingAmount = (int) (amount * 100);

        int numberOfOneDollars = remainingAmount / 100;
        remainingAmount = remainingAmount % 100;

        int numberOfQuarters = remainingAmount / 25;
        remainingAmount = remainingAmount % 25;

        int numberOfDimes = remainingAmount / 10;
        remainingAmount = remainingAmount % 10;

        int numberOfNickels = remainingAmount / 5;
        remainingAmount = remainingAmount % 5;

        int numberOfPennies = remainingAmount;

        System.out.println("Your amount " + amount + " consists of");
        if (numberOfOneDollars > 0)
            System.out.println(" " + numberOfOneDollars
                    + (numberOfOneDollars > 1 ? " dollars" : " dollar"));
        if (numberOfQuarters > 0)
            System.out.println(" " + numberOfQuarters
                    + (numberOfQuarters > 1 ? " quarters" : " quarter"));
        if (numberOfDimes > 0)
            System.out.println(" " + numberOfDimes
                    + (numberOfDimes > 1 ? " dimes" : " dime"));
        if (numberOfNickels > 0)
            System.out.println(" " + numberOfNickels
                    + " nickel");
        if (numberOfPennies > 0)
            System.out.println(" " + numberOfPennies
                    + (numberOfPennies > 1 ? " pennies" : " pennie"));
    }
}