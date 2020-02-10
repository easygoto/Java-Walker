package dp.iterator;

/**
 * @author trink
 */
public class Cat {

    private int id;

    Cat(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cat: " + id;
    }
}
