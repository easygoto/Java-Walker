package dp.Strategy;

public class Cat implements Comparable {

    private int width;
    private int height;

    public Cat(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public int compareTo(Object object) {
        if (object instanceof Cat) {
            Cat cat  = (Cat) object;
            int temp = Integer.compare(this.getWidth(), cat.getWidth());
            return temp == 0 ? Integer.compare(this.getHeight(), cat.getHeight()) : temp;
        }
        return -100;
    }
}
