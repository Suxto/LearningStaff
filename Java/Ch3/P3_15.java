package Ch3;

import java.util.Map;
import java.util.Scanner;

public class P3_15 {
    public static void main(String[] args) {

        int[] arr = new int[2];
        arr[1] = (int) (1000 * Math.random());
        System.out.print("Enter a three-digit number: ");
        arr[0] = new Scanner(System.in).nextInt();
        if (arr[0] < 100 || arr[0] > 999) {
            System.out.print("Invalid number!");
            return;
        }

        if (arr[0] == arr[1]) {
            System.out.print("You won 10000 dollars!");
            return;
        }
        int[] vis = new int[10];//0-9
        for (int i = 0; i < 3; i++) {
            vis[arr[0] % 10]++;
            arr[0] /= 10;
        }
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            if (vis[arr[1] % 10]-- > 0) ans++;
            arr[1] /= 10;
        }
        if (ans == 3) System.out.print("You won 3000 dollars!");
        else if (ans > 0) System.out.print("You won 1000 dollars!");
        else System.out.print("You lose!");
    }
}
