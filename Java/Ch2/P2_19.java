package Ch2;

import java.util.Scanner;

public class P2_19 {
    public static void main(String[] args) {
        System.out.println("Enter the coordinates of three points separated by spaces");
        System.out.print("like x1 y1 x2 y2 x3 y3: ");
        Scanner scanner = new Scanner(System.in);
        double[] x = new double[3];
        double[] y = new double[3];
        double[] edge = new double[3];
        for (int i = 0; i < 3; i++) {
            x[i] = scanner.nextDouble();
            y[i] = scanner.nextDouble();
            if (i > 0)
                edge[i] = Math.sqrt(Math.pow(x[i] - x[i - 1], 2) + Math.pow(y[i] - y[i - 1], 2));
        }
        edge[0] = Math.sqrt(Math.pow(x[2] - x[0], 2) + Math.pow(y[2] - y[0], 2));
        double s = (edge[0] + edge[1] + edge[2]) / 2;
        System.out.println("The area of the triangle is " + Math.sqrt(s * (s - edge[0]) * (s - edge[1]) * (s - edge[2])));

    }
}
