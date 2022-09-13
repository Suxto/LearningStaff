package Ch3;

import java.util.Arrays;

public class P3_4 {
    static String[] months = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    public static void main(String[] args) {
        System.out.print(months[(int) (Math.random() * months.length)]);
    }
}
