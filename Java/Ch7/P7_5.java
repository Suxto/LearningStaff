package Ch7;

import java.util.Scanner;

import static java.lang.System.*;

public class P7_5 {
    public static void main(String[] args) {
        out.print("Enter 10 numbers: ");
        Scanner scanner = new Scanner(in);
        int cnt = 10, idx = 0;
        int[] arr = new int[10];
        boolean[] vis = new boolean[1000000000];
        while (cnt-- > 0) {
            int t = scanner.nextInt();
            if (vis[t]) continue;
            arr[idx++] = t;
            vis[t] = true;
        }
        out.println("The number of distinct numbers is " + idx);
        out.print("The distinct numbers are: ");
        for (int i = 0; i < idx; i++) {
            out.print(arr[i] + " ");
        }
    }
}
