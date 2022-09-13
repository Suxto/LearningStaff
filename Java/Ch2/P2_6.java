package Ch2;

import java.util.Scanner;

public class P2_6 {
    public static void main(String[] args) {
        System.out.print("Enter a number between 0 and 1000: ");
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(), sum = 0;
        while (t > 0) {
            sum += t % 10;
            t /= 10;
        }
        System.out.println("The number of the digits is " + sum);
    }
}
