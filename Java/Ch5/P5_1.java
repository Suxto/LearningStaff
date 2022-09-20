package Ch5;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class P5_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        out.print("Enter an integer, the input ends if it is 0: ");
        double sum = 0, t = scanner.nextDouble();
        int pNum = 0, nNum = 0;
        if (t == 0) {
            out.print("No number are entered except 0");
            return;
        }
        while (t != 0) {
            if (t > 0) pNum++;
            else nNum++;
            sum += t;
            t = scanner.nextDouble();
        }
        out.print("The number of positives is " + pNum + '\n');
        out.print("The number of negative is " + nNum + '\n');
        out.print("The total is " + sum + '\n');
        out.print("The average is " + sum / (pNum + nNum) + '\n');
    }
}
