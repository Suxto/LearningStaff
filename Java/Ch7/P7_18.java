package Ch7;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P7_18 {
    public static void main(String[] args) {
        out.print("Enter 10 numbers: ");
        Scanner scanner = new Scanner(in);
        double[] arr = new double[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = scanner.nextDouble();
        }
        scanner.close();
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                if (arr[i] > arr[j]) {
                    double temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        out.println("Sorted array: ");
        for (double d : arr) out.print(d + " ");
    }
}
