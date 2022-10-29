package Ch12;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P12_7 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String str = scanner.next();
        int ans;
        try {
            ans = binToDec(str);
            out.println(ans);
        } catch (NumberFormatException e) {
            out.println("Invalid bin number: " + e.toString().substring(e.toString().lastIndexOf('.') + 1));
        }
    }

    public static int binToDec(String s) throws NumberFormatException {
        int beg = s.charAt(0) == '-' ? 1 : 0;
        int ans = 0;
        for (int i = beg; i < s.length(); i++) {
            ans *= 2;
            char ch = s.charAt(i);
            if (Character.isLetter(ch) || ch > '1')
                throw new NumberFormatException();
            ans += ch - '0';
        }
        return ans * (beg == 1 ? -1 : 1);
    }
}
