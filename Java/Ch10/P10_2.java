package Ch10;

public class P10_2 {
    public static void main(String[] args) {

    }
}

class BMI {
    String name;
    int age;
    double weight;//pound
    double height;//inch
    public static final double KILOGRAMS_PRE_POUND = 0.45359237;
    public static final double METERS_PRE_INCH = 0.0254;

    public BMI(String name, int age, double weight, double height) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public BMI(String name, int age, double weight, double feet, double inch) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = 12 * feet + inch;
    }

    public double getBMI() {
        double bmi = weight * KILOGRAMS_PRE_POUND / (height * METERS_PRE_INCH);
        return Math.round(bmi * 100) / 100.0;
    }

    public String getStatus() {
        double bmi = getBMI();
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }
}