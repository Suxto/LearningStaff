package Ch12;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

public class P12_11 {
//    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String str = args[0];
        String path = args[1];
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(fileReader);
        ArrayList<String> arrayList = new ArrayList<>();
        while (scanner.hasNext()) {
            arrayList.add(scanner.next());
        }
        try {
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < arrayList.size(); i++) {
            int idx = arrayList.get(i).indexOf(str);
//            out.println(arrayList.get(i));
            if (idx == -1) continue;
            arrayList.set(i, arrayList.get(i).substring(idx + str.length()));
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            for (String s : arrayList) {
                if("".equals(s))continue;
                fileWriter.write(s + " ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
