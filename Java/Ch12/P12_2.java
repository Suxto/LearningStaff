package Ch12;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P12_2 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int a, b;
        while (true) {
            try {
                out.print("Enter two integers: ");
                a = scanner.nextInt();
                b = scanner.nextInt();
                out.println(a + " + " + b + " = " + (a + b));
                break;
            } catch (InputMismatchException e) {
                out.print("Please input integer!\n");
                scanner.nextLine();
            }
        }
    }
}
