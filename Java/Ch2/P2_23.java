package Ch2;

import java.util.Scanner;

public class P2_23 {
    public static void main(String[] args) {
        System.out.print("Enter the driving distance: ");
        Scanner scanner = new Scanner(System.in);
        double dis = scanner.nextDouble();
        System.out.print("Enter miles per gallon: ");
        double mpg = scanner.nextDouble();
        System.out.print("Enter price per gallon: ");
        double ppg = scanner.nextDouble();
        System.out.print("The cost of driving is $" + dis / mpg * ppg);
    }
}
