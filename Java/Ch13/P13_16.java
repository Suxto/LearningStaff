package Ch13;

import java.util.Arrays;

import static java.lang.System.out;

public class P13_16 {
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static boolean isOp(char op) {
        return (op == '+' || op == '-' || op == '*' || op == '/');
    }

    public static void main(String[] args) {
        String str = args[0];
        String[] strs = str.split("/");
//        out.print(Arrays.toString(strs));

        int n1 = Integer.parseInt(strs[0]);
        int n4 = Integer.parseInt(strs[2]);
        str = strs[1];
        char op = 0;
        for (int i = 0; i < str.length(); i++) {
            if (isOp(str.charAt(i))) {
                op = str.charAt(i);
                break;
            }
        }
        String tmp = " \\%s ".formatted(op);
        strs = str.split(tmp);
//        out.print(Arrays.toString(strs));
        int n2 = Integer.parseInt(strs[0]);
        int n3 = Integer.parseInt(strs[1]);

        out.printf("%d/%d %c %d/%d ", n1, n2, op, n3, n4);
        int lcm = n2 * n4 / gcd(n2, n4);
        int sum = 1;
        if (op == '*') {
            sum = n1 * n3;
            lcm = n2 * n4;
        } else {
            n1 = n1 * lcm / n2;
            n3 = n3 * lcm / n4;
            switch (op) {
                case '+':
                    sum = n1 + n3;
                    break;
                case '-':
                    sum = n1 - n3;
                    break;
            }
        }
        int gcd = gcd(sum, lcm);
        out.printf("= %d/%d", sum / gcd, lcm / gcd);
    }
}
