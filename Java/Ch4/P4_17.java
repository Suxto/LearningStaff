package Ch4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class P4_17 {


    public static void main(String[] args) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] months = {
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Set", "Oct", "Nov", "Dec"
        };
        ArrayList<String> Mon = new ArrayList<String>(List.of(months));
        out.print("Enter a year: ");
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        out.print("Enter a month: ");
        String str = scanner.next();
        int ind = Mon.indexOf(str);
        if (ind == -1) {
            out.print(str + " is not a correct month name");

        } else {
            if (ind == 1 && year % 4 == 0 && year % 100 != 0 || year % 400 == 0) daysInMonth[1]++;
            out.print(str + " " + year + " has " + daysInMonth[ind] + " days");
        }
    }
}
