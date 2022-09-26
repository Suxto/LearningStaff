package Ch7;

import java.util.Scanner;

import static java.lang.System.*;

public class P7_10 {
    public static int indexOfSmallestElement(double[] arr) {
        int pos = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[pos]) pos = i;
        }
        return pos;
    }

    public static void main(String[] args) {
        out.print("Enter 10 numbers: ");
        Scanner scanner = new Scanner(in);
        double[] arr = new double[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = scanner.nextDouble();
        }
        scanner.close();
        out.print("The index of the minimum number is " + indexOfSmallestElement(arr));
    }
}
