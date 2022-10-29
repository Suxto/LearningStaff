package Ch12;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static java.lang.System.out;

public class P12_19 {
//    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        final String urlStr = "http://liveexample.pearsoncmg.com/data/Lincoln.txt";
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
        int cnt = 0;
        while (scanner.hasNext()) {
            scanner.next();
            cnt++;
        }
        out.println("The number of words is " + cnt);
    }
}
