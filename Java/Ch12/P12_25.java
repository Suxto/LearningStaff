package Ch12;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

public class P12_25 {
//    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        final String urlStr = "http://liveexample.pearsoncmg.com/data/Salary.txt";
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
        Map<String, Double> sum = new HashMap<>();
        Map<String, Integer> cnt = new HashMap<>();
        while (scanner.hasNext()) {
            scanner.next();
            scanner.next();
            String level = scanner.next();
            Double d = scanner.nextDouble();
            if (!sum.containsKey(level)) {
                sum.put(level, d);
                cnt.put(level, 1);
            } else {
                sum.replace(level, sum.get(level) + d);
                cnt.replace(level, cnt.get(level) + 1);
            }
        }
        var itS = sum.entrySet().iterator();
        var itC = cnt.entrySet().iterator();
        double sumAll = 0;
        int cntAll = 0;

        while (itS.hasNext()) {
            var now = itS.next();
            var tmp = itC.next();
            if (now.getKey().equals(tmp.getKey()))
                out.printf("The sum salary of %s is %.2f, the average salary of %s is %f\n", now.getKey(), now.getValue(), now.getKey(), now.getValue() / tmp.getValue());
            sumAll += now.getValue();
            cntAll += tmp.getValue();
        }
        out.printf("The sum salary of everyone is %f, the average salary of everyone is %f", sumAll, sumAll / cntAll);
    }
}
