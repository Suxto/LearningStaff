package Ch8;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P8_4 {

    public static void main(String[] args) {
        class stu implements Comparable<stu> {
            public final int sum;
            public final int num;

            stu(int num, int sum) {
                this.num = num;
                this.sum = sum;
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder("Employee");
                sb.append(" ").append(num).append(" ").append(sum);
                return sb.toString();
            }

            @Override
            public int compareTo(stu o) {
                return o.sum - this.sum;
            }
        }
        stu[] e = new stu[8];
        Scanner scanner = new Scanner(in);
        int[][] arr = new int[8][7];
        for (int i = 0; i < 8; i++) {
            int sum = 0;
            for (int j = 0; j < 7; j++) {
                sum += arr[i][j] = scanner.nextInt();
            }
            e[i] = new stu(i, sum);
        }
        Arrays.sort(e);
        for (stu s : e) {
            out.println(s);
        }
    }
}
