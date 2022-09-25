package Ch7;

import static java.lang.System.*;

public class P7_7 {
    public static void main(String[] args) {
        int[] vis = new int[10];
        for (int i = 0; i < 100; i++) {
            int rand = (int) (Math.random() * 10);
            vis[rand]++;
        }
        for (int i = 0; i < 10; i++) {
            out.println(i + " occurs " + vis[i] + " times");
        }
    }
}
