package Ch2;

import Ch1.Main;

import java.util.Scanner;

public class P2_2 {
    public static void main(String[] args) {
        System.out.print("Enter the radius and length of a cylinder: ");
        Scanner scanner = new Scanner(System.in);
        final double R = scanner.nextDouble();
        final double H = scanner.nextDouble();
        System.out.println("The area is " + R * R * Math.PI);
        System.out.println("The volume is " + H * R * R * Math.PI);
    }
}
