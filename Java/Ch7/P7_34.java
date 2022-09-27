package Ch7;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P7_34 {
    public static String sort(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.print("Enter a string: ");
        out.print("String sorted: " + sort(scanner.next()));
    }
}
