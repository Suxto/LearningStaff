package Ch4;

import java.util.Scanner;

import static java.lang.System.out;

public class P4_21 {
    public static void main(String[] args) {
        out.print("Enter a SSN: ");
        String str = new Scanner(System.in).next();
        out.print(str + " is ");
        if (str.length() != 11) {
            out.print("an in");
        } else {
            boolean flag = true;
            for (int i = 0; i < 3; i++) {
                char ch = str.charAt(i);
                if (ch < '0' || ch > '9') {
                    flag = false;
                    break;
                }
            }
            if (str.charAt(3) != '-') flag = false;
            if (flag)
                for (int i = 4; i < 6; i++) {
                    char ch = str.charAt(i);
                    if (ch < '0' || ch > '9') {
                        flag = false;
                        break;
                    }
                }
            if (str.charAt(6) != '-') flag = false;
            if (flag)
                for (int i = 7; i < 11; i++) {
                    char ch = str.charAt(i);
                    if (ch < '0' || ch > '9') {
                        flag = false;
                        break;
                    }
                }
            if (flag) out.print("a");
            else out.print("an in");
        }
        out.print("valid social security number");
    }
}
