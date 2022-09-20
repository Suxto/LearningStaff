package Ch4;

import java.util.Scanner;

import static java.lang.System.out;

import static java.lang.Math.*;

public class P4_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        out.print("Enter the number of sides: ");
        final double sidesNum = scanner.nextDouble();
        out.print("Enter the side: ");
        final double sideLen = scanner.nextDouble();
        double ans = sidesNum * sideLen * sideLen;
        ans /= 4 * tan(PI / sidesNum);
        out.print("The area of the polygon is " + ans);
    }
}
