package Etc;

public class Main {
    int i = 7;

    Main() {
        setI(20);
        System.out.println(i);
    }

    void setI(int i) {
        this.i = i * 2;
    }

    public static void main(String[] args) {
        Main main = new Main1();
    }
}

class Main1 extends Main {
    Main1() {
    }

    @Override
    void setI(int i) {
        this.i = i * 3;
    }

}