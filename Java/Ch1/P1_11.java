package Ch1;

public class P1_11 {
    public static void main(String[] args) {
        double pop = 312_032_486.0;
        long seconds = 60 * 60 * 24 * 365;
        double add = seconds / 7.0 + seconds / 45.0;
        double minus = seconds / 13.0;
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " Years Later : ");
            pop += add;
            pop -= minus;
            System.out.printf("%f\n", pop);
        }
    }
}
