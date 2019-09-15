package generics.demo;

public class Computer {

    private String name;
    private int    price;

    public Computer(String name, int price) {
        super();
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Computer setName(String name) {
        this.name = name;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Computer setPrice(int price) {
        this.price = price;
        return this;
    }

    public String toString() {
        return this.name + " computer is " + this.price;
    }
}
