package Ch4;

import java.util.Scanner;

import static java.lang.Math.*;
import static java.lang.System.out;

public class P4_9 {

    public static void main(String[] args) {
        out.print("Enter a character: ");
        String str = new Scanner(System.in).next();
        char ch = str.charAt(0);
        out.print("The Unicode for the character " + ch + " is " + (int) ch);
    }
}
