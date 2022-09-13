package Ch2;

import java.util.Scanner;

public class P2_4 {
    public static void main(String[] args) {
        System.out.print("Enter a number in pounds: ");
        Scanner scanner = new Scanner(System.in);
        final double PD = scanner.nextDouble();
        System.out.println(PD + " pounds is " + PD * 0.454 + " kilograms");
    }
}
