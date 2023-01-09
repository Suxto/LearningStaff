package Etc;

import javafx.animation.Timeline;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.setAction(() -> System.out.println(2));
        Timeline timeline = new Timeline();

    }

    public void setAction(T1 t) {
        t.m();
    }
}

interface T1 {
    public void m();
}