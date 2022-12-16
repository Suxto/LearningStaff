package Ch13;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P13_4 {

    public static boolean isLeap(int n) {
        return (n % 4 == 0 && (n % 100 != 0 || n % 400 == 0));
    }

    public static int zeller(int y, int m) {
        if (m < 3) {
            m += 12;
            y--;
        }
        int j = y / 100, k = y % 100;

        int q = 1;
        int cnt = (q + 26 * (m + 1) / 10 + k + k / 4 + j / 4 + 5 * j) % 7;
        return (cnt + 6) % 7;
    }

    public static void main(String[] args) {
        final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//        Scanner scanner = new Scanner(in);
//        out.print("Enter full year year: ");
//        int year = scanner.nextInt();
        int year = Integer.parseInt(args[1]);
        if (isLeap(year)) daysInMonth[1]++;
//        out.print("Enter the month: ");
//        int month = scanner.nextInt();
        int month = Integer.parseInt(args[0]);
        int offset = zeller(year, month);
//        for (int i = 0; i < 12; i++) {
        out.println("\t\t\t" + months[month - 1] + ",  " + year);
        out.print("------------------------------------\n");
        out.print("\tSun\tMon\tTue\tWed\tThu\tFri\tSat\n\t");
        for (int j = 0; j < offset; j++)
            out.print(" \t");
        for (int j = 1; j <= daysInMonth[month - 1]; j++) {
            out.print(j + "\t");
            if ((offset + j) % 7 == 0) out.print("\n\t");
        }
//            out.print("\n");
//        }
    }

}
