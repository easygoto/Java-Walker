import generics.demo.Computer;
import generics.demo.InterImpl;
import generics.demo.Tool;
import org.junit.Test;

public class GenericsDemo {

    @Test
    public void test() {
        Tool<Computer> com = new Tool<>(new Computer("Acer", 5000));
        InterImpl<String> str = new InterImpl<>();
        InterImpl<Computer> com2 = new InterImpl<>();
        str.show("123456");
        com2.show(new Computer("Acer", 5000));

        System.out.println(com.getQ().setName("hehe").getName());
        com.show(new Computer("Acer", 5000));
    }
}
