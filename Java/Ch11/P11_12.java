package Ch11;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P11_12 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        out.print("Enter 5 numbers: ");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) arrayList.add(scanner.nextInt());
        out.println("The sum is " + sum(arrayList));
    }

    public static int sum(ArrayList<Integer> arrayList) {
        int sum = 0;
        for (Integer i : arrayList) sum += i;
        return sum;
    }
}