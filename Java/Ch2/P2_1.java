package Ch2;

import java.util.Scanner;

public class P2_1 {
    public static void main(String[] args) {
        System.out.print("Enter a degree in Celsius: ");
        Scanner scanner = new Scanner(System.in);
        double t = scanner.nextDouble();
        System.out.println(t + " Celsius is " + (t / 5.0 * 9 + 32) + " Fahrenheit");
    }
}
