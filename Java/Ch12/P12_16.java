package Ch12;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class P12_16 {
//    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String path = args[0];
        String oldstr = args[1];
        String newstr = args[2];
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
            if (oldstr.equals(arrayList.get(i))) {
                arrayList.set(i, newstr);
            }
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            for (String s : arrayList) {
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
