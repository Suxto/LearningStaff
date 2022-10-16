package Ch10;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P10_14 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        out.println(new MyDate());
        out.println(new MyDate(34355555133101L));
    }
}

class MyDate {
    int year, month, day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public MyDate() {
        this(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue() - 1, LocalDate.now().getDayOfMonth());
    }

    public MyDate(long x) {
        this(LocalDate.ofInstant(Instant.ofEpochMilli(x), ZoneId.systemDefault()).getYear(), LocalDate.ofInstant(Instant.ofEpochMilli(x), ZoneId.systemDefault()).getMonth().getValue() - 1, LocalDate.ofInstant(Instant.ofEpochMilli(x), ZoneId.systemDefault()).getDayOfMonth());
    }

    public void setDate(long el) {
        MyDate myDate = new MyDate(el);
        this.year = myDate.year;
        this.month = myDate.month;
        this.day = myDate.day;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return year + "-" + (month + 1) + "-" + day;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}


