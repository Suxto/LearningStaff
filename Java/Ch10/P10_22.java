package Ch10;

import java.math.BigInteger;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P10_22 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        out.println(MyString1.valueOf(scanner.nextInt()));
    }
}

class MyString1 {
    final private char[] arr;
    private int size = 0;

    public MyString1(char[] chars) {
        arr = chars.clone();
        for (int i = 0; i < chars.length; i++) {
            if (arr[i] == 0) {
                size = i + 1;
                break;
            }
        }
        if (size == 0) size = arr.length;
    }

    public char charAt(int x) {
        return arr[x];
    }

    int length() {
        return size;
    }

    public MyString1 substring(int begin, int end) {
        int len = end - begin + 1;
        char[] tmp = new char[len];
        System.arraycopy(arr, begin, tmp, 0, len);
        return new MyString1(tmp);
    }

    public MyString1 toLowerCase() {
        char[] tmp = new char[size];
        for (int i = 0; i < size; i++) {
            tmp[i] = Character.toLowerCase(arr[i]);
        }
        return new MyString1(tmp);
    }

    public boolean equals(MyString1 s) {
        for (int i = 0; i < Math.min(this.size, s.size); i++) {
            if (this.arr[i] != s.arr[i]) return false;
        }
        return this.size == s.size;
    }

    public static MyString1 valueOf(int i) {
        int[] tmp = new int[40];
        int len = 0;
        while (i > 0) {
            tmp[len++] = i % 10;
            i /= 10;
        }
        char[] temp = new char[len];
        for (i = 0; i < len; i++) {
            temp[i] = (char) (tmp[len - i - 1] + '0');
        }
        return new MyString1(temp);
    }

    public String toString() {
        return new String(arr);
    }
}