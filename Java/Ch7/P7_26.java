package Ch7;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P7_26 {
    public static boolean equals(int[] l1, int[] l2) {
        if (l1.length != l2.length) return false;
        for (int i = 0; i < l1.length; i++) {
            if (l1[i] != l2[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.print("Enter list1 size and contents: ");
        int[] l1 = new int[scanner.nextInt()];
        for (int i = 0; i < l1.length; i++) l1[i] = scanner.nextInt();
        out.print("Enter list2 size and contents: ");
        int[] l2 = new int[scanner.nextInt()];
        for (int i = 0; i < l2.length; i++) l2[i] = scanner.nextInt();
        scanner.close();
        out.print("Two lists are ");
        if (!equals(l1, l2)) out.print("not ");
        out.print("strictly identical");
    }
}
