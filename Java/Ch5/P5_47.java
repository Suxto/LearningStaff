package Ch5;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P5_47 {

    public static void main(String[] args) {
        out.print("Enter the first 12 digits of an ISBN-13 as a string: ");
        String str = new Scanner(in).next();
        if (str.length() != 12) out.print(str + " is an invalid input");
        else {
            int sum = 0;
            for (int i = 0; i < str.length(); i++) {
                int now = str.charAt(i) - '0';
                if ((i & 1) == 0) sum += now;
                else sum += 3 * now;
            }
            sum = 10 - sum % 10;
            sum%=10;
            out.print("The ISBN-13 number is " + str + sum);
        }
    }
}
