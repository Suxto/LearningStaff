package Ch9;

import java.util.Date;

import static java.lang.System.out;

public class P9_3 {
    public static void main(String[] args) {
        Date date = new Date();
        for (long i = 10000; i <= 100000000000L; i *= 10) {
            date.setTime(i);
            System.out.println(date.toString());
        }
    }
}
