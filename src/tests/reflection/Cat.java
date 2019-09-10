package tests.reflection;

public class Cat {

    private String name;

    public String getName() {
        return name;
    }

    public Cat setName(String name) {
        this.name = name;
        return this;
    }
}
