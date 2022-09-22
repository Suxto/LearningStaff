package Ch6;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P6_24 {

    public static boolean isLeap(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    public static void main(String[] args) {
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        long totalMilliseconds = System.currentTimeMillis();

        long currentMilliseconds = totalMilliseconds % 1000;
        long totalSeconds = totalMilliseconds / 1000;

        long currentSeconds = totalSeconds % 60;
        long totalMinutes = totalSeconds / 60;

        long currentMinutes = totalMinutes % 60;
        long totalHours = totalMinutes / 60;
        long currentHours = totalHours % 24;
        long totalDays = totalHours / 24;

        final long leapYearsDay = 366, normalYearsDay = 365;
        int years = 1970;
        while (totalDays >= 0) {
            if (isLeap(years++)) totalDays -= leapYearsDay;
            else totalDays -= normalYearsDay;
        }
        int currentYears = years - 1;

        boolean leap = isLeap(currentYears);
        if (leap) {
            totalDays += 367;
        } else {
            totalDays += 366;
        }

        int currentMonths = 0;
        if (isLeap(currentYears)) daysInMonth[1]++;
        int tot = 0;
        for (int i = 1; i < 12; i++) {
            tot += daysInMonth[i];
            if (tot > totalDays) {
                totalDays -= tot - daysInMonth[i - 1] + 1;
                currentMonths = i;
                break;
            }
        }

        out.println(currentYears + "/" + currentMonths + "/" + totalDays + " " + currentHours + ":" + currentMinutes + ":" + currentSeconds + " " + currentMilliseconds);
    }


}