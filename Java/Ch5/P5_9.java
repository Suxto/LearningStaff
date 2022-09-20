package Ch5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import static java.lang.System.out;

public class P5_9 {
    public static void main(String[] args) {
        class User implements Comparable {
            public int score;
            public String name;

            User() {
                super();
            }

            @Override
            public int compareTo(Object o) {
                User u = (User) o;
                if (u.score < this.score) return -1;
                else return 1;
            }

            @Override
            public String toString() {
                return name + ", " + score;
            }
        }
        Scanner scanner = new Scanner(System.in);
        out.print("Enter the number of the student: ");
        int n = scanner.nextInt();
        ArrayList<User> list = new ArrayList<>();

        while (n-- > 0) {
            User nw = new User();
            out.print("Enter the name: ");
            nw.name = scanner.next();
            out.print("Enter the score: ");
            nw.score = scanner.nextInt();
            list.add(nw);
        }
        Collections.sort(list);
//        out.print(list);
        for (int i = 0; i < 2; i++) {
            out.print("Name: " + list.get(i).name);
            out.print("Score: " + list.get(i).score);
            out.print('\n');
        }
    }


}


