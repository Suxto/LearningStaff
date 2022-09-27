package Ch7;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P7_35 {
    static String[] words = {"write", "that", "hello", "program", "java"};
    static Scanner scanner = new Scanner(in);

    public static boolean done(char[] s) {
        for (char ch : s) if (ch == '*') return false;
        return true;
    }

    public static void go() {
        String str = words[(int) (Math.random() * words.length)];

        char[] star = new char[str.length()];
        boolean[] vis = new boolean[26];
        Arrays.fill(star, '*');
        int cnt = 0;
        while (!done(star)) {
            out.print("(Guess) Enter a letter in word ");
            out.print(star);
            out.print(" > ");
            String temp = scanner.next();
            char ch = temp.charAt(0);
            boolean flag = true;
            if (vis[ch - 'a']) {
                out.print("\t\t" + ch + " is already in the word\n");
            } else {
                for (int i = 0; i < str.length(); i++) {
                    if (ch == str.charAt(i)) {
                        star[i] = ch;
                        flag = false;
                        vis[ch - 'a'] = true;
                    }
                }
                if (flag) {
                    out.print("\t\t" + ch + " is not in the word\n");
                    cnt++;
                }
            }
        }
        out.println("The word is " + str + ". You missed " + cnt + (cnt > 1 ? " times" : " time"));
    }

    public static void main(String[] args) {
        char ch;
        do {
            go();
            out.print("Do you want to guess another word? Enter y or n> ");
            ch = scanner.next().charAt(0);
            while (ch != 'y' && ch != 'n') {
                out.print("Invalid input! Please input y or n: ");
                ch = scanner.next().charAt(0);
            }
        } while (ch == 'y');
    }
}
