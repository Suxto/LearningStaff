package Ch11;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P11_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        out.print("Enter an array end with 0: ");
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (scanner.hasNext()) {
            int i = scanner.nextInt();
            if (i == 0) break;
            else arrayList.add(i);
        }
        shuffle(arrayList);
        for (var s : arrayList) {
            out.println(s);
        }
    }

    public static void shuffle(ArrayList<Integer> list) {
        Random random = new Random(new Date().getTime());
        for (int i = 0; i < list.size(); i++) {
            int rand = random.nextInt(list.size());
            Integer t = list.get(i);
            list.set(i, list.get(rand));
            list.set(rand, t);
        }
    }
}
