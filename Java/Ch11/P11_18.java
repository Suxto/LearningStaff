package Ch11;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P11_18 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        out.print("Enter a string: ");
        out.print("The list is "+toCharacterArray(scanner.next()));
    }

    public static ArrayList<Character> toCharacterArray(String s) {
        ArrayList<Character> arrayList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            arrayList.add(s.charAt(i));
        }
        return arrayList;
    }
}