package Ch3;

import java.util.Scanner;

public class P3_21 {
    static String[] arr = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter year: ");
        int y = scanner.nextInt();
        System.out.print("Enter month: ");
        int m = scanner.nextInt();
        if (m < 3) {
            m += 12;
            y--;
        }
        int j = y / 100, k = y % 100;
        System.out.print("Enter the day of the month: ");
        int q = scanner.nextInt();
        int cnt = (q + 26 * (m + 1) / 10 + k + k / 4 + j / 4 + 5 * j) % 7;
        System.out.print("Day of the week is " + arr[cnt]);
    }
}
