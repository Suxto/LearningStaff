package Ch4;

import java.util.Scanner;

import static java.lang.System.out;

public class P4_13 {

    public static void main(String[] args) {
        out.print("Enter a Letter: ");
        String str = new Scanner(System.in).next();
        char ch = str.toUpperCase().charAt(0);
        if (ch < 'A' || ch > 'Z') {
            out.print(str.charAt(0) + " is an in valid input");
            return;
        }
        switch (ch) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                out.print(str.charAt(0) + " is a vowel");
                break;
            default:
                out.print(str.charAt(0) + " is a consonant");
        }
    }
}
