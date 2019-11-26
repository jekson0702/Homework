package homework;

public class Person extends Organism implements INewInterface {

    private String name;


    public void setName(String name) {
        this.name = name;
    }

    public void showInfo() {
        System.out.println("The name of the person is " + name);
    }

    public String getName() {
        return name;
    }
}
