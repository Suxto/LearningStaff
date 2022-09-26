package Ch7;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P7_13 {
    public static int getRandom(int... numbers) {
        while (true) {
            boolean flag = true;
            int rand = (int) (Math.random() * 54) + 1;
            for (int i : numbers) {
                if (i == rand) {
                    flag = false;
                    break;
                }
            }
            if (flag) return rand;
        }
    }

    public static void main(String[] args) {
        out.print("Enter the the number of your input: ");
        Scanner scanner = new Scanner(in);
        int x = scanner.nextInt();
        int[] arr = new int[x];
        for (int i = 0; i < x; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.close();
        out.print("The random number is " + getRandom(arr));
    }
}
