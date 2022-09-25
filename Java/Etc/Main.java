package Etc;

public class Main {
    public static void main(String[] args) {
        int sum = 0;
        int item = 0;
        do {
            item++;
            if (sum >= 4)
                continue;
            sum += item;
        }
        while (item < 5);
        System.out.print(sum);
    }
}
