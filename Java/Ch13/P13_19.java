package Ch13;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P13_19 {
    public static void main(String[] args) {
        out.print("Enter a decimal number: ");
        Scanner scanner = new Scanner(in);
        String str = scanner.next();
        String[] strings = str.split("\\.");
//        out.print(Arrays.toString(strings));
        int sign = strings[0].charAt(0) == '-' ? -1 : 1;
        if (sign == -1) strings[0] = strings[0].substring(1);
        int len = strings[1].length();
        str = strings[0] + strings[1];
        BigInteger fenzi = new BigInteger(str);
        BigInteger fenmu = new BigInteger((int) Math.pow(10, len) + "");
        BigInteger gcd = fenzi.gcd(fenmu);
        out.print("The fraction number is " + fenzi.divide(gcd).multiply(BigInteger.valueOf(sign)) + "/" + fenmu.divide(gcd));
    }
}
