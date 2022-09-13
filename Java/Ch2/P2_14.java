package Ch2;

import java.util.Scanner;

public class P2_14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter weight in pounds: ");
        double weight = scanner.nextDouble() * 0.45359237;
        System.out.print("Enter height in inches: ");
        double height = scanner.nextDouble() * 0.0254;
        System.out.println("BMI is " + weight / height / height);
    }
}
