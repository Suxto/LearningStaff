package Ch6;

import static java.lang.Math.*;
import static java.lang.System.*;

public class P6_30 {
    public static int go() {
        int a = (int) (random() * 6 + 1), b = (int) (random() * 6 + 1);
        int sum = a + b;
        out.printf("You rolled %d + %d = %d\n", a, b, sum);
        return sum;
    }

    public static void main(String[] args) {
        int now = go();
        if (now == 2 || now == 3 || now == 12) {
            out.print("You lose");
            return;
        }
        if (now == 7 || now == 11) {
            out.print("You win");
            return;
        }
        out.print("Your point is " + now + "\n");
        while (true) {
            int rush = go();
            if (rush == 7) {
                out.print("You win");
                return;
            }
            if (rush == now) {
                out.print("You lose");
                return;
            }
            out.println("Your point is " + rush);
        }
    }
}