package Ch6;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

class P6_37 {

    public static int getSize(long num) {
        int cnt = 0;
        while (num > 0) {
            num /= 10;
            cnt++;
        }
        return cnt;
    }

    public static String format(int num, int wid) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(num);
        int n = getSize(num);
        if (wid <= n) return stringBuilder.toString();
        wid -= n;
        while (wid-- > 0) stringBuilder.insert(0, '0');
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.print("Enter a number in integer: ");
        int n = scanner.nextInt();
        out.print("Enter the width you want: ");
        out.print(format(n, scanner.nextInt()));
    }

}