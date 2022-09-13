package Ch2;

import java.util.Scanner;

public class P2_12 {
    public static void main(String[] args) {
        System.out.print("Enter speed and acceleration: ");
        Scanner scanner = new Scanner(System.in);
        double speed = scanner.nextDouble();
        double acc = scanner.nextDouble();
        System.out.println("The minimum ranway length for this airplane is " + speed * speed / 2.0 / acc);
    }
}
