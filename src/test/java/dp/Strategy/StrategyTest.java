package dp.Strategy;

import org.junit.Before;
import org.junit.Test;

public class StrategyTest {

    private Cat[] cats;
    private Dog[] dogs;
    private Pig[] pigs;

    @Before
    public void data() {
        cats = new Cat[]{
                new Cat(12, 34),
                new Cat(23, 49),
                new Cat(28, 57),
                new Cat(459, 82),
                new Cat(75, 36),
                new Cat(38, 74)
        };

        dogs = new Dog[]{
                new Dog(12, 34),
                new Dog(23, 49),
                new Dog(28, 57),
                new Dog(459, 82),
                new Dog(75, 36),
                new Dog(38, 74)
        };

        pigs = new Pig[]{
                new Pig(12, 34),
                new Pig(23, 49),
                new Pig(28, 57),
                new Pig(459, 82),
                new Pig(75, 36),
                new Pig(38, 74)
        };
    }

    @Test
    public void comparable() {

        DataSorter.sort(cats);
        DataSorter.show(cats);

        DataSorter.sort(dogs);
        DataSorter.show(dogs);
    }

    @Test
    public void comparator() {

        DataSorter.sort(pigs);
        DataSorter.show(pigs);
    }
}
