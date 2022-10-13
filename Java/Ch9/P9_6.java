package Ch9;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import static java.lang.System.out;

public class P9_6 {
    private static void gen(int[] arr) {
        Random random = new Random(2333);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(Integer.MAX_VALUE);
        }
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        int[] arr = new int[100_000];
        gen(arr);
        stopWatch.start();
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            int tmp = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            arr[i] = arr[min];
            arr[min] = tmp;
        }
        stopWatch.stop();
        out.printf("It costs %d ms.", stopWatch.getElapsedTime());
    }


}

class StopWatch {
    long startTime, endTime;

    StopWatch() {
    }

    void start() {
        startTime = new Date().getTime();
    }

    void stop() {
        endTime = new Date().getTime();
    }

    long getElapsedTime() {
        return endTime - startTime;
    }
}