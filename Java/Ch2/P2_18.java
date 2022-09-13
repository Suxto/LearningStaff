package Ch2;

public class P2_18 {
    public static void main(String[] args) {
        System.out.println("a\tb\tpow(a,b)");
        int a = 1, b = 2;
        for (int i = 0; i < 5; i++) {
            System.out.printf("%d\t%d\t%d\n", a, b, (int) Math.pow(a, b));
            a = b++;
        }
    }
}
