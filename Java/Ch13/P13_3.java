package Ch13;

import java.util.ArrayList;

import static java.lang.System.out;

public class P13_3 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 10; i > 0; i--) {
            arrayList.add(i);
        }
        sort(arrayList);
        out.print(arrayList);
    }

    static public void sort(ArrayList<Integer> list) {
//        java.util.Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) > list.get(j)) {
                    int t = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, t);
                }
            }
        }
    }
}
