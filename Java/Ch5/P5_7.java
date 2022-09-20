package Ch5;

import static java.lang.System.out;

public class P5_7 {
    public static void main(String[] args) {
        double now = 10500, sum = 0;
        for (int i = 1; i <= 13; i++) {
            now *= 1.05;
            if (i > 9)
//                out.printf("%f\n", now);
                sum += now;
        }
        out.print(now);
    }
}
