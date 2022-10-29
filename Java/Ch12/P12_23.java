package Ch12;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static java.lang.System.out;

public class P12_23 {
//    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        final String urlStr = "http://liveexample.pearsoncmg.com/data/Scores.txt";
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(url.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int cnt = 0, sum = 0;
        while (scanner.hasNextInt()) {
            sum += scanner.nextInt();
            cnt++;
        }
        out.printf("The sum of the scores is %d \nThe average is %f", sum, (double) sum / cnt);
    }
}
