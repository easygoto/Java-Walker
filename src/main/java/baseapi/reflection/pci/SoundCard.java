package baseapi.reflection.pci;

public class SoundCard implements PCI {
    @Override
    public void open() {
        System.out.println("sound card open...");
    }

    @Override
    public void close() {
        System.out.println("sound card close...");
    }
}
