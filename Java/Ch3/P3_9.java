package Ch3;

import java.util.Scanner;

public class P3_9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first 9 digits of an ISBN as integer: ");
        int tmp = scanner.nextInt(), last = 0;
        System.out.print("The ISBN-10 number is " + tmp);
        for (int i = 9; i > 0; i--) {
            last += tmp % 10 * i;
            tmp /= 10;
        }
        last %= 11;
        System.out.print(last == 10 ? "X" : last);
    }
}
