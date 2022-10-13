package Ch9;

import java.util.GregorianCalendar;
import java.util.Random;

import static java.lang.System.out;

public class P9_5 {
    public static void main(String[] args) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        out.print(gregorianCalendar.get(GregorianCalendar.YEAR) + " ");
        out.print(gregorianCalendar.get(GregorianCalendar.MONTH) + " ");
        out.println(gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH));
        gregorianCalendar.setTimeInMillis(12345678765L);
        out.print(gregorianCalendar.get(GregorianCalendar.YEAR) + " ");
        out.print(gregorianCalendar.get(GregorianCalendar.MONTH) + " ");
        out.println(gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH));
    }
}
