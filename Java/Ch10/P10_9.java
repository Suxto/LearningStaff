package Ch10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static java.lang.System.out;

public class P10_9 {
    public static void main(String[] args) {
        Course course = new Course("A Course");
        course.addStudent("A");
        course.addStudent("B");
        course.addStudent("C");
        course.dropStudent("B");
        for (String s : course.getStudents()) {
            out.println(s);
        }
    }
}

class Course {
    private final String courseName;
    private String[] students = new String[100];
    private int numberOfStudents;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    void dropStudent(String str) {
        int i = 0;
        for (; i < numberOfStudents; i++) {
            if (students[i].equals(str)) break;
        }
        numberOfStudents -= 1;
        for (; i < numberOfStudents; i++) {
            students[i] = students[i + 1];
        }
    }

    public void addStudent(String student) {
        if (numberOfStudents == students.length) {
            students = Arrays.copyOfRange(students, 0, numberOfStudents * 2);
        }
        students[numberOfStudents++] = student;
    }

    public String[] getStudents() {
        return Arrays.copyOfRange(students, 0, numberOfStudents);
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void clear() {
        students = new String[100];
    }
}
