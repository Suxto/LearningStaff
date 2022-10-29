package Ch12;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

public class P12_10 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int[] arr;
//        while (true) {
        try {
            arr = new int[Integer.MAX_VALUE];
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            arr = null;
            gc();
//                break;
//            }
        }
    }
}
