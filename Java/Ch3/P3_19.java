package Ch3;

import java.util.Arrays;
import java.util.Scanner;

public class P3_19 {
    public static void main(String[] args) {
        int[] edges = new int[3];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter three edges of an triangle as integer: ");
        for (int i = 0; i < 3; i++)
            edges[i] = scanner.nextInt();
        Arrays.sort(edges);
//        System.out.print(Arrays.toString(edges));
        int tmp = edges[0] + edges[1];
        if (tmp > edges[2]) System.out.print("The perimeter of the triangle is " + (tmp + edges[2]));
        else System.out.print("Invalid triangle!");
    }
}
