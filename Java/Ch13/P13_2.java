package Ch13;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class P13_2 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }
        shuffle(arrayList);
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
