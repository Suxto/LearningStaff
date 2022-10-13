package Ch9;

import java.util.Date;

import static java.lang.System.out;

public class P9_8 {
    public static void main(String[] args) {
        Fan fan1 = new Fan();
        Fan fan2 = new Fan();
        fan1.setSpeed(Fan.FAST);
        fan1.setRadius(10);
        fan1.setColor("Yellow");
        fan1.setOn(true);
        fan2.setSpeed(Fan.MEDIUM);
        out.println("Fan1:\n" + fan1);
        out.print("Fan2:\n" + fan2);
    }
}

class Fan {
    public static final int SLOW = 1, MEDIUM = 2, FAST = 3;
    private int speed = SLOW;
    private boolean on = false;
    private double radius = 5;
    String color = "blue";

    Fan() {
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!on) stringBuilder.append("fan is off");
        else stringBuilder.append("Speed = ").append(speed);
        stringBuilder.append("\nColor = ").append(color).append("\nRadius = ").append(radius);
        return stringBuilder.toString();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}