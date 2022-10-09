package Ch8;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P8_32 {
    public static Scanner scanner = null;
    static int[] vis = new int[10000000];

    public static void input(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = scanner.nextDouble();
            }
        }
    }

    public static double getTriangleArea(double[][] points) {
        double s = 0;
        double[] edge = new double[3];
        for (int i = 0; i < 3; i++) {
            s += edge[i] = Math.sqrt(Math.pow(points[i][0] - points[(i + 1) % 3][0], 2) + Math.pow(points[i][1] - points[(i + 1) % 3][1], 2));
        }
        s /= 2;
        return Math.sqrt(s * (s - edge[0]) * (s - edge[1]) * (s - edge[2]));
    }


    public static void main(String[] args) {
        scanner = new Scanner(in);
        double[][] a = new double[3][2];
        out.print("Enter x1, x1, x2, y2, x3, y3: ");
        input(a);
        double ans = getTriangleArea(a);
        if (Double.isNaN(ans)) out.print("Three points are on the same line");
        else out.printf("The area of the triangle is %.2f", ans);
    }
}
/*
2.5 2 5 -1.0 4.0 2.0

2 2 4.5 4.5 6 6
 */