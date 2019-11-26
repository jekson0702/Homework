package homework;

public class Example {
    public static void main(String[] args) {
        Student student = new Student();
        student.whoIAM();
        System.out.println(Student.sum(5, 7));
        System.out.println(Student.subtraction(5, 7));
        Person person = new Person();
        student.setName("Evgeniy");
        person.setName("Zhenya");
        student.showInfo();
        person.showInfo();
    }
}
