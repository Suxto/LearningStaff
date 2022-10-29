package Ch12;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P12_9 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String str = scanner.next();
        int ans;
        try {
            ans = hexToDec(str);
            out.println(ans);
        } catch (BinaryFormatException e) {
            out.println("Invalid hex number: " + e);
        }
    }

    public static int hexToDec(String s) throws BinaryFormatException {
        int beg = s.charAt(0) == '-' ? 1 : 0;
        int ans = 0;
        for (int i = beg; i < s.length(); i++) {
            ans *= 2;
            char ch = s.charAt(i);
            if (Character.isLetter(ch) || ch > '1')
                throw new BinaryFormatException();
            ans += ch - '0';
        }

        return ans * (beg == 1 ? -1 : 1);
    }
}

class BinaryFormatException extends Exception {
    @Override
    public String toString() {
        return "BinaryFormatException";
    }
}
