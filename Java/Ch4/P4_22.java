package Ch4;

import java.util.Scanner;

import static java.lang.System.out;

public class P4_22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        out.print("Enter string s1: ");
        String s1 = scanner.next();
        out.print("Enter string s2: ");
        String s2 = scanner.next();
        out.print(s2 + " is ");
        int ind = s1.indexOf(s2);
        if (ind == -1) out.print("not ");
        out.print("a substring of " + s1);
    }
}
