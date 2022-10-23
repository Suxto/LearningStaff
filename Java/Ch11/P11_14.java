package Ch11;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P11_14 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        out.print("Enter five integers for list1: ");
        listIn(arrayList1);
        out.print("Enter five integers for list2: ");
        listIn(arrayList1);
        out.print("The combined list is ");
        arrayList1.forEach(i -> out.print(i + " "));
//        arrayList2.forEach(i -> {
//            if (!arrayList1.contains(i)) out.print(i + " ");
//        });
    }

    public static void listIn(ArrayList<Integer> arrayList) {
        for (int i = 0; i < 5; i++) {
            arrayList.add(scanner.nextInt());
        }
    }
}