package Ch6;

import java.util.Scanner;

import static java.lang.System.*;

public class P6_18 {
    public static boolean isValidPassword(String str) {
        int cnt = 0;
        if (str.length() < 8) return false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                if (Character.isDigit(ch)) cnt++;
            } else return false;
        }
        return cnt > 1;
    }

    public static void main(String[] args) {
        out.print("Enter your password: ");
        if (isValidPassword(new Scanner(in).next()))
            out.print("Valid Password");
        else out.print("Invalid Password");
    }
}
