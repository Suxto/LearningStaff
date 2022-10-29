package Ch12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P12_28 {

    public static void main(String[] args) {
        for (String s : args) {
            if (s.startsWith("Exercise")) {
                File file = new File(s);
                int idx = s.lastIndexOf('_');
                if (s.length() - idx == 2)
                    file.renameTo(new File(s.substring(0, idx + 1) + "0" + s.charAt(s.length() - 1)));
            }
        }

    }
}
