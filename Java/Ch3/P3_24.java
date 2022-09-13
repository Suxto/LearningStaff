package Ch3;

public class P3_24 {
    static String[] num = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", " Jack", "Queen", "King"};
    static String[] type = {"Clubs", "Diamonds", "Hearts", "Spades"};

    public static void main(String[] args) {
        int x = (int) (14 * Math.random());
        int y = (int) (4 * Math.random());
        System.out.printf("The card you picked is %s of %s", num[x], type[y]);
    }
}
