package Ch11;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.lang.System.out;

public class P11_2 {
    public static void main(String[] args) {
        Person p = new Person("Jack");
        out.println(p);
        Student s = new Student("Tom");
        out.println(s);
        Employee e = new Employee("Jerry");
        out.println(e);
        Faculty f = new Faculty("Ben");
        out.println(f);
        Stuff st = new Stuff("Hank");
        out.println(st);
    }
}

class Person {
    String name, address, tel, email;

    Person(String name) {
        this.name = name;
    }

    public String toString() {
        String s = this.getClass().getName();
        return s.substring(s.indexOf('.') + 1) + "  " + this.name;
    }
}

class Student extends Person {
    Student(String name) {
        super(name);
    }

    public enum Status {
        freshman, Sophomore, junior, senior
    }

    Status classStatus;
}

class Employee extends Person {
    String office;
    int salary;
    MyDate dateHired;

    Employee(String name) {
        super(name);
    }
}

class Faculty extends Employee {
    String level;
    String workTime;

    Faculty(String name) {
        super(name);
    }
}

class Stuff extends Employee {
    String header;

    Stuff(String name) {
        super(name);
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