package Ch3;

import java.util.Scanner;

public class P3_30 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the time zone offset to GMT: ");
        final int OFFSET = scanner.nextInt();
        long currentTimeMillis = System.currentTimeMillis();
        long totalSeconds = currentTimeMillis / 1000;
        long currentSeconds = totalSeconds % 60;
        long currentMinutes = totalSeconds / 60 % 60;
        long currentHorus = (totalSeconds / 3600 + OFFSET) % 24;
        Boolean flag = false;
        if (currentHorus > 12) {
            currentHorus -= 12;
            flag = true;
        }
        System.out.print("The current time is " + currentHorus);
        System.out.print(":" + currentMinutes);
        System.out.print(":" + currentSeconds);
        System.out.print(flag ? " PM" : " AM");
    }
}
