package study.reflection.pci;

public class MainBoard implements PCI {
    @Override
    public void open() {
        System.out.println("main board open...");
    }

    @Override
    public void close() {
        System.out.println("main board close...");
    }

    public void run() {
        System.out.println("main board run...");
    }

    public void usePCI(PCI pci) {
        if (pci != null) {
            pci.open();
            pci.close();
        }
    }
}
