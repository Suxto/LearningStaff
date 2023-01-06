package BigHomeWork.LargeNumber;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;


public class LargeNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        System.out.print("Please enter the first number: ");
        LargeNumber a = LargeNumber.valueOf(scanner.next());
        while (a == null) {
            System.out.print("Please enter a valid number: ");
            a = LargeNumber.valueOf(scanner.next());
        }
        System.out.print("Please enter the second number: ");
        LargeNumber b = LargeNumber.valueOf(scanner.next());
        while (b == null) {
            System.out.print("Please enter a valid number: ");
            b = LargeNumber.valueOf(scanner.next());
        }
        System.out.println("Please select a method: ");
        System.out.print("1.add 2.subtract 3.multiply 4.divide :");
        char op = scanner.next().charAt(0);
        while (op < '1' || op > '4') {
            System.out.print("Please enter a number between 1 and 4\n1.add 2.subtract 3.multiply 4.divide : ");
            op = scanner.next().charAt(0);
        }
        if (op == '1') System.out.println(a + " + " + b + " = " + a.add(b));
        else if (op == '2') System.out.println(a + " - " + b + " = " + a.subtract(b));
        else if (op == '3') System.out.println(a + " * " + b + " = " + a.multiply(b));
        else System.out.println(a + " / " + b + " = " + a.divide(b));
    }

    public LargeNumber add(LargeNumber b) {
        if (val.length < b.val.length) return b.add(this);
        ArrayList<Byte> list = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < val.length; i++) {
            t += this.val[i];
            if (i < b.val.length) t += b.val[i];
            list.add((byte) (t % 10));
            t /= 10;
        }

        if (t > 0) list.add((byte) t);
        return new LargeNumber(list);
    }

    public LargeNumber subtract(LargeNumber b) {
        ArrayList<Byte> list = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < val.length; i++) {
            t = val[i] - t;
            if (i < b.val.length) t -= b.val[i];
            list.add((byte) ((t + 10) % 10));
            if (t < 0) t = 1;
            else t = 0;
        }
        while (list.size() > 1 && list.get(list.size() - 1) == 0) list.remove(list.size() - 1);
        return new LargeNumber(list);
    }

    public LargeNumber multiply(int b) {
        ArrayList<Byte> list = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < val.length || t > 0; i++) {
            if (i < val.length) t += val[i] * b;
            list.add((byte) (t % 10));
            t /= 10;
        }
        while (list.size() > 1 && list.get(list.size() - 1) == 0) list.remove(list.size() - 1);

        return new LargeNumber(list);
    }

    public LargeNumber multiply(LargeNumber b) {
        LargeNumber largeNumber = new LargeNumber(0);
        //low -> high
        for (int i = 0; i < b.val.length; i++) {
            LargeNumber tmp = this.multiply(b.val[i]);
            byte[] t = new byte[tmp.val.length + i];
            System.arraycopy(tmp.val, 0, t, i, tmp.val.length);
            tmp.val = t;
            largeNumber = largeNumber.add(tmp);
        }
        return largeNumber;
    }

    public LargeNumber divide(int b) {
        ArrayList<Byte> list = new ArrayList<>();
        int r = 0;
        for (int i = val.length - 1; i >= 0; i--) {
            r = r * 10 + val[i];
            list.add((byte) (r / b));
            r %= b;
        }
        for (int i = 0; i < (list.size() >> 1); i++) {
            byte t = list.get(i);
            list.set(i, list.get(list.size() - i - 1));
            list.set(list.size() - i - 1, t);
        }
        while (list.size() > 1 && list.get(list.size() - 1) == 0) list.remove(list.size() - 1);
        return new LargeNumber(list);
    }

    public LargeNumber divide(LargeNumber b) {
        if (equals(b)) return LargeNumber.valueOf(1);
        if (!largerOrEqualThan(b)) return LargeNumber.valueOf(0);
        if (b.val.length < 30) return divide(Integer.parseInt(b.toString()));
        LargeNumber cnt = new LargeNumber(1);
        LargeNumber cmp = new LargeNumber(b.val.clone());
        while (largerOrEqualThan(cmp)) {
            cmp = cmp.add(b);
            cnt = cnt.add(LargeNumber.valueOf(1));
        }
        if (cmp.equals(b)) cnt.subtract(LargeNumber.valueOf(1));
        return cnt;
    }

    public boolean equals(LargeNumber b) {
        if (val.length != b.val.length) return false;
        for (int i = val.length - 1; i >= 0; i--)
            if (val[i] != b.val[i]) return false;
        return true;
    }

    public boolean largerOrEqualThan(LargeNumber b) {
        if (val.length != b.val.length) return val.length > b.val.length;
        for (int i = val.length - 1; i >= 0; i--)
            if (val[i] != b.val[i]) return val[i] > b.val[i];
        return true;
    }


    byte[] val = null;

    private LargeNumber(ArrayList<Byte> list) {
        val = new byte[list.size()];
        int pos = 0;
        for (Byte b : list) {
            val[pos++] = b;
        }
    }

    private LargeNumber(byte[] val) {
        this.val = val;
    }

    private LargeNumber(int x) {
        val = new byte[x];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = val.length - 1; i >= 0; i--) stringBuilder.append(val[i]);
        return stringBuilder.toString();
    }

    public static LargeNumber valueOf(String str) {
        LargeNumber largeNumber = new LargeNumber(str.length());
        int pos = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            byte b = (byte) (str.charAt(i) - '0');
            if (b < 0 || b > 9) return null;
            largeNumber.val[pos++] = b;
        }
        return largeNumber;
    }

    public static LargeNumber valueOf(long x) {
        return LargeNumber.valueOf(x + "");
    }

    public static LargeNumber valueOf(int x) {
        return LargeNumber.valueOf(x + "");
    }
}
