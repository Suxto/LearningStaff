package Ch2;

import java.util.Scanner;

public class P2_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the time zone offset to GMT: ");
        final int OFFSET = scanner.nextInt();
        long currentTimeMillis = System.currentTimeMillis();
        long totalSeconds = currentTimeMillis / 1000;
        long currentSeconds = totalSeconds % 60;
        long currentMinutes = totalSeconds / 60 % 60;
        long currentHorus = (totalSeconds / 3600 + OFFSET) % 24;
        System.out.print("The current time is " + currentHorus);
        System.out.print(":" + currentMinutes);
        System.out.print(":" + currentSeconds);
    }
}
