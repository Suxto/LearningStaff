package Ch10;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P10_18 {
//    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger(String.valueOf(Long.MAX_VALUE));
        int cnt = 5;
        while (cnt > 0) {
            boolean flag = true;
            BigInteger boundary = bigInteger.sqrt();
            for (BigInteger i = BigInteger.TWO; i.compareTo(boundary) < 0; i = i.add(BigInteger.ONE)) {
                if (bigInteger.mod(i).equals(BigInteger.ZERO)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                out.println(bigInteger);
                cnt--;
            }
            bigInteger = bigInteger.add(BigInteger.ONE);
        }
    }
}