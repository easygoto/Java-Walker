package dp.strategy;

/**
 * @author trink
 */
public class Dog implements Comparable {

    private int food;
    private int weight;

    public Dog(int food, int weight) {
        this.food = food;
        this.weight = weight;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Object object) {
        if (object instanceof Dog) {
            Dog dog  = (Dog) object;
            int temp = Integer.compare(this.getFood(), dog.getFood());
            return temp == 0 ? Integer.compare(this.getWeight(), dog.getWeight()) : temp;
        }
        return -100;
    }
}
