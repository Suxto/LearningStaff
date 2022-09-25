package Ch7;

import java.util.Scanner;

import static java.lang.System.*;

public class P7_9 {
    public static double min(double[] arr) {
        double minn = Double.MAX_VALUE;
        for (double d : arr)
            minn = Math.min(minn, d);
        return minn;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.print("Enter 10 numbers: ");
        double[] arr = new double[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = scanner.nextDouble();
        }
        out.print("The minimum number is " + min(arr));
    }
}
