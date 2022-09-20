package Ch4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class P4_20 {
    public static void main(String[] args) {
        out.print("Enter a string: ");
        String str = new Scanner(System.in).next();
        out.print("The length of the string is " + str.length() + " and the first char is " + str.charAt(0));
    }
}
