package Ch4;

import java.util.Scanner;

import static java.lang.Math.*;
import static java.lang.System.out;

public class P4_25 {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            char ch = (char) (random() * 26 + 'A');
            out.write(ch);
        }
        for (int i = 0; i < 4; i++) {
            char ch = (char) (random() * 10 + '0');
            out.write(ch);
        }
    }
}
