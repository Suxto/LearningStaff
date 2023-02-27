package Ch7;

import java.util.Scanner;

import static java.lang.System.*;

public class P7_3 {
    public static void main(String[] args) {
        out.print("Enter the integers between 1 and 100: ");
        Scanner scanner = new Scanner(in);
        int[] vis = new int[101];
        int temp;
        while ((temp = scanner.nextInt()) != 0) {
            vis[temp]++;
            temp=1;
        }
        for (int i = 0; i < 101; i++) {
            if (vis[i] > 0) {
                out.println(i + " occurs " + vis[i] + (vis[i] > 1 ? " times" : " time"));
            }
        }
    }
}
