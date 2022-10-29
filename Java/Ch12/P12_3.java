package Ch12;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P12_3 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        Random random = new Random(new Date().getTime());
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt();
        }
        try {
            int x = scanner.nextInt();
            out.println(arr[x]);
        }catch (ArrayIndexOutOfBoundsException e){
            out.println("Out of bound!");
        }
    }
}
