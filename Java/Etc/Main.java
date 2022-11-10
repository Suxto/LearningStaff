package Etc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

class Main {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int type = scanner.nextInt();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
        if (type == 1 || type == 2) {
            LocalDate now;
            now = getLocalDate();
            if (now == null) return;
            out.print(now.format(dateTimeFormatter));
            long go = scanner.nextInt();
            if (type == 1) out.printf(" next %d days is:%s\n", go, now.plusDays(go).format(dateTimeFormatter));
            else out.printf(" previous %d days is:%s\n", go, now.minusDays(go).format(dateTimeFormatter));
        } else if (type == 3) {
            LocalDate d1 = getLocalDate();
            if (d1 == null) return;
            LocalDate d2 = getLocalDate();
            if (d2 == null) return;

            long until = Math.abs(d1.until(d2, ChronoUnit.DAYS));
            out.printf("The days between %s and %s are:%d\n", d1.format(dateTimeFormatter), d2.format(dateTimeFormatter), until);
        } else out.println("Wrong Format");
    }

    private static LocalDate getLocalDate() {
        LocalDate now;
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if (a < 1820 || a > 2020 || b < 1 || b > 12 || c < 1 || c > 31) {
            out.println("Wrong Format");
            return null;
        }
        try {
            now = LocalDate.of(a, b, c);
        } catch (Exception ignore) {
            out.println("Wrong Format");
            return null;
        }
        return now;
    }
}
