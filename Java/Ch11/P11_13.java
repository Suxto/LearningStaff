package Ch11;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P11_13 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        out.print("Enter 10 integers: ");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int x = scanner.nextInt();
            if (!arrayList.contains(x)) arrayList.add(x);
        }
        out.print("The distinct integer are ");
        arrayList.forEach(i -> out.print(i + " "));
    }

    public static int sum(ArrayList<Integer> arrayList) {
        int sum = 0;
        for (Integer i : arrayList) sum += i;
        return sum;
    }
}