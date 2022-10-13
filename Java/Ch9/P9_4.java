package Ch9;

import java.util.Date;
import java.util.Random;

import static java.lang.System.out;

public class P9_4 {
    public static void main(String[] args) {
        Random random = new Random(1000);
        for (int i = 0; i < 50; i++) {
            out.print(random.nextInt(100)+" ");
        }
    }
}
