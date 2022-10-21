package Ch11;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.out;

public class P11_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        out.print("Enter an array end with 0: ");
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (scanner.hasNext()) {
            int i = scanner.nextInt();
            if (i == 0) break;
            else arrayList.add(i);
        }
        out.println("The maximum element in the array is " + max(arrayList));
    }

    public static Integer max(ArrayList<Integer> list) {
        if (list == null || list.size() == 0) return null;
        Integer maxn = list.get(0);
        for (Integer i : list) if (i > maxn) maxn = i;
        return maxn;
    }
}
