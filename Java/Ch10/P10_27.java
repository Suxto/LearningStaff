package Ch10;

import java.util.Arrays;

import static java.lang.System.out;

public class P10_27 {

    public static void main(String[] args) {
        MyStringBuilder1 m = new MyStringBuilder1("hello");
        out.println(m.substring(1, 3));
    }
}

class MyStringBuilder1 {
    final private char[] chars;

    public MyStringBuilder1(String s) {
        chars = s.toCharArray();
    }

    public MyStringBuilder1(char[] chars1) {
        chars = chars1.clone();
    }

    public MyStringBuilder1(MyStringBuilder1 m) {
        chars = m.chars.clone();
    }

    public int length() {
        return chars.length;
    }

    public MyStringBuilder1 append(int i) {
        char[] chars1 = Arrays.copyOfRange(chars, 0, chars.length + 1);
        chars1[chars.length] = (char) (i + '0');
        return new MyStringBuilder1(chars1);
    }

    public char charAt(int idx) {
        return chars[idx];
    }

    public MyStringBuilder1 toLowerCase() {
        char[] chars1 = this.chars.clone();
        for (int i = 0; i < chars1.length; i++) {
            chars1[i] = Character.toLowerCase(chars1[i]);
        }
        return new MyStringBuilder1(chars1);
    }

    public MyStringBuilder1 substring(int begin, int end) {
        char[] chars1 = Arrays.copyOfRange(this.chars, begin, end);
        return new MyStringBuilder1(chars1);
    }

    public String toString() {
        return new String(chars);
    }
}