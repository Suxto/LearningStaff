package Ch10;

import java.time.Instant;

import static java.lang.System.out;

public class P10_3 {
    public static void main(String[] args) {
        String s = "233";
        MyInteger i1 = new MyInteger(233);
        MyInteger i2 = new MyInteger(2);
        MyInteger i3 = new MyInteger(MyInteger.parseInt(s));
        out.print(i1.equals(i2) + "\n");
        out.print(i1.equals(i3) + " " + i3.val + "\n");
        out.print(i1.isPrime());
    }
}

class MyInteger {
    int val;

    public static boolean isPrime(int val) {
        if (val == 2) return true;
        for (int i = 2; i * i < val; i++) {
            if (val % i == 0) return false;
        }
        return true;
    }

    public static int parseInt(char[] str) {
        int res = 0;
        for (char c : str) {
            res = res * 10 + c - '0';
        }
        return res;
    }

    public static int parseInt(String str) {
        return parseInt(str.toCharArray());
    }

    public boolean equals(int val) {
        return val == this.val;
    }

    public boolean equals(MyInteger val) {
        return val.val == this.val;
    }

    public static boolean isOdd(int val) {
        return (val & 1) == 1;
    }

    public static boolean isEven(int val) {
        return (val & 1) == 0;
    }

    public static boolean isPrime(MyInteger val) {
        return isPrime(val.val);
    }

    public static boolean isOdd(MyInteger val) {
        return (val.val & 1) == 1;
    }

    public static boolean isEven(MyInteger val) {
        return (val.val & 1) == 0;
    }

    public boolean isPrime() {
        return isPrime(val);
    }

    boolean isOdd() {
        return (val & 1) == 1;
    }

    boolean isEven() {
        return (val & 1) == 0;
    }

    public int getVal() {
        return val;
    }

    public MyInteger(int val) {
        this.val = val;
    }
}