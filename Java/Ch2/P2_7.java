package Ch2;

import java.util.Scanner;

public class P2_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of minutes: ");
        long min = scanner.nextLong();
        System.out.println(min + " minutes is approximately " + min / 60 / 24 / 365 + " years and " + min / 60 / 24 % 365 + " days");
    }
}
