package Ch3;

import java.util.Scanner;

public class P3_12 {
    public static void main(String[] args) {
        System.out.print("Enter a three-digit integer: ");
        int num = new Scanner(System.in).nextInt();
        if (num % 100 == 0 || num > 999) {
            System.out.print("Invalid number");
            return;
        }
        System.out.print(num + " is " + (num / 100 == num % 10 ? "" : "not ") + "a palindrome");
    }
}
