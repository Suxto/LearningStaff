package Ch7;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P7_27 {
    public static boolean equals(int[] l1, int[] l2) {
        if (l1.length != l2.length) return false;
        Arrays.sort(l1);
        Arrays.sort(l2);
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
        out.print("identical");
    }
}
