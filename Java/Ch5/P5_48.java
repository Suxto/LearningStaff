package Ch5;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P5_48 {

    public static void main(String[] args) {
        out.print("Enter a string: ");
        String str = new Scanner(in).nextLine();
        for (int i = 0; i < str.length(); i++) {
            if ((i & 1) == 0) out.write(str.charAt(i));
        }
        out.flush();
    }
}
