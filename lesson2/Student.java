package homework;

public class Student extends Person {
    private int age = 25;

    @Override
    public void showInfo() {
        System.out.println("The name of the Student is " + super.getName());

    }

    public void whoIAM() {
        System.out.println(" I am a student");
    }

    private int myAge() {
        return age;
    }

    protected static int sum(int a, int b) {
        return a + b;
    }

    static int subtraction(int a, int b) {
        return a - b;
    }


}
