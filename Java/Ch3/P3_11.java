package Ch3;

import java.util.Scanner;

public class P3_11 {

    static String[] months = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    static int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the year as integer: ");
        int year = scanner.nextInt();
        System.out.print("Enter the month as integer: ");
        int month = scanner.nextInt();

        if (month > 12 || month < 1) {
            System.out.print("invalid month!");
            return;
        }

        if (month == 2) {
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    if (year % 400 == 0) daysInMonth[2]++;
                } else daysInMonth[2]++;
            }
        }
        System.out.print(months[month] + " " + year + " has " + daysInMonth[month] + " days");
    }
}
