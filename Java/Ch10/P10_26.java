package Ch10;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P10_26 {

    public static void main(String[] args) {
        String str = args[0];
        int len = str.length();
        int a = 0, b = 0;
        int i = 0;
        char ch = str.charAt(i);
        while (Character.isDigit(ch)) {
            a *= 10;
            a += ch - '0';
            i++;
            if (i >= len) break;
            ch = str.charAt(i);
        }

        while (i < len && Character.isSpaceChar(str.charAt(i))) i++;

        char op = str.charAt(i++);

        while (i < len && Character.isSpaceChar(str.charAt(i))) i++;

        ch = str.charAt(i);
        while (Character.isDigit(ch)) {
            b *= 10;
            b += ch - '0';
            i++;
            if (i >= len) break;
            ch = str.charAt(i);
        }
        int ans = 0;
        if (op == '+') ans = a + b;
        if (op == '-') ans = a - b;
        if (op == '*') ans = a * b;
        if (op == '/') ans = a / b;
        out.print(a + " " + op + " " + b + " = " + ans);
    }
}
