package Ch11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P11_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        Course course = new Course("Java");
        for (int i = 0; i < 3; i++) {
            course.addStudent(scanner.next());
        }
        for (String s : course.getStudents()) {
            out.println(s);
        }
    }
}

class Course {
    private final String courseName;
    private final ArrayList<String> students = new ArrayList<>();
    private int numberOfStudents;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    void dropStudent(String str) {
        int i = 0;
        for (; i < numberOfStudents; i++) {
            if (students.get(i).equals(str)) break;
        }
        numberOfStudents -= 1;
        students.remove(i);
    }

    public void addStudent(String student) {
        students.add(student);
    }

    public String[] getStudents() {
        return students.toArray(new String[0]);
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void clear() {
        students.clear();
    }
}
