package Ch5;

import java.util.Scanner;

import static java.lang.System.*;

public class P5_29 {
    static boolean isLeap(int n) {
        return n % 4 == 0 && (n % 400 == 0 || n % 100 != 0);
    }

    public static void main(String[] args) {
        final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        Scanner scanner = new Scanner(in);
        out.print("Please input the year: ");
        int year = scanner.nextInt();
        if (isLeap(year)) daysInMonth[1]++;
        out.print("Please input the Weekday on January the 1st " + year + ": ");
        int offset = scanner.nextInt();
        for (int i = 0; i < 12; i++) {
            out.println("\t\t\t" + months[i] + " " + year);
            out.print("-------------------------------------\n");
            out.print("\tSun\tMon\tTue\tWed\tThu\tFri\tSat\n\t");
            for (int j = 0; j < offset; j++) out.print(" \t");
            for (int j = 1; j <= daysInMonth[i]; j++) {
                out.print(j + "\t");
                if ((offset + j) % 7 == 0) out.print("\n\t");
            }
            out.print("\n");
        }
    }
}
