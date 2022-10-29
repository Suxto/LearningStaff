package Ch12;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P12_30 {

    public static void main(String[] args) {
        out.print("Enter a filename: ");
        Scanner scanner = new Scanner(in);
        String path = scanner.next();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner = new Scanner(fileReader);
        int[] cnt = new int[256];
        while (scanner.hasNext()) {
            String str = scanner.next();
            for (int i = 0; i < str.length(); i++) {
                cnt[str.charAt(i)]++;
            }
        }
        try {
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            if (cnt[i] > 0)
                out.printf("Number of %cs: %d\n", i, cnt[i]);
        }
        for (int i = 'a'; i <= 'z'; i++) {
            if (cnt[i] > 0)
                out.printf("Number of %cs: %d\n", i, cnt[i]);
        }
    }
}
