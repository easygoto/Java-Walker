package tests.reflection;

public class Person {

    private String name;
    private int    age;
    private Cat    cat;

    public Person(Cat cat) {
        this.cat = cat;
    }

    public Cat getCat() {
        return cat;
    }

    public Person setCat(Cat cat) {
        this.cat = cat;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }
}