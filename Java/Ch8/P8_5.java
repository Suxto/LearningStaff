package Ch8;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P8_5 {
    public static Scanner scanner = null;

    public static double[][] addMatrix(double[][] a, double[][] b) {
        double[][] Mat = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                Mat[i][j] = a[i][j] + b[i][j];
            }
        }
        return Mat;
    }

    public static void input(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = scanner.nextDouble();
            }
        }
    }

    public static void output(double[][] a, double[][] b, double[][] c) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                out.print(a[i][j] + " ");
            }
            if (i == a.length >> 1) out.print(" +  ");
            else out.print("    ");
            for (int j = 0; j < a[i].length; j++) {
                out.print(b[i][j] + " ");
            }
            if (i == b.length >> 1) out.print(" =  ");
            else out.print("    ");
            for (int j = 0; j < a[i].length; j++) {
                out.print(c[i][j] + " ");
            }
            out.print("\n");
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(in);
        double[][] a = new double[3][3];
        double[][] b = new double[3][3];
        out.print("Enter matrix1: ");
        input(a);
        out.print("Enter matrix2: ");
        input(b);
        out.println("The matrices are added as follows");
        output(a, b, addMatrix(a, b));
    }
}
