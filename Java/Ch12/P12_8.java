package Ch12;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P12_8 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String str = scanner.next();
        int ans;
        try {
            ans = hexToDec(str);
            out.println(ans);
        } catch (HexFormatException e) {
            out.println("Invalid hex number: " + e);
        }
    }

    public static int hexToDec(String s) throws HexFormatException {
        int beg = s.charAt(0) == '-' ? 1 : 0;
        int ans = 0;
        for (int i = beg; i < s.length(); i++) {
            ans *= 16;
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) ch = Character.toLowerCase(ch);
            if (!Character.isDigit(ch) || ch > 'f') {
                throw new HexFormatException();
            }
            ans += Character.isDigit(ch) ? ch - '0' : 10 + ch - 'a';
        }
        return ans * (beg == 1 ? -1 : 1);
    }
}

class HexFormatException extends Exception {
    @Override
    public String toString() {
        return "HexFormatException";
    }
}
