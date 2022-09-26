package Ch7;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P7_17 {
    public static void main(String[] args) {
        class stu implements Comparable<stu> {
            public String name;
            public int score;

            stu() {
                name = null;
                score = 0;
            }

            stu(String n, int s) {
                name = n;
                score = s;
            }

            @Override
            public int compareTo(stu a) {
                return a.score - this.score;
            }
        }
        out.print("Enter the the number of the students: ");
        Scanner scanner = new Scanner(in);
        int x = scanner.nextInt();
        stu[] arr = new stu[x];
        for (int i = 0; i < x; i++) {
            out.print("Enter the name: ");
            String str = scanner.next();
            out.print("Enter the score: ");
            int s = scanner.nextInt();
            arr[i]=new stu(str, s);
        }
        Arrays.sort(arr);
        scanner.close();
        out.print("Name\t\tScore\n");
        out.print("------------------------\n");
        for (stu s : arr) {
            out.println(s.name + "\t\t\t" + s.score);
        }
    }
}
