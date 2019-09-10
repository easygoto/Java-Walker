package study.lang;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

interface PCI {
    public void open();

    public void close();
}

class MainBoard {
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

class SoundCard implements PCI {
    public void open() {
        System.out.println("sound card open...");
    }

    public void close() {
        System.out.println("sound card close...");
    }
}

class NetCard implements PCI {
    public void open() {
        System.out.println("net card open...");
    }

    public void close() {
        System.out.println("net card close...");
    }
}

public class Reflect {

    public static void main(String[] args) throws Exception {

        MainBoard mb = new MainBoard();
        mb.run();
        Properties      prop = new Properties();
        FileInputStream fis  = new FileInputStream(new File("pci.properties"));
        prop.load(fis);
        for (int x = 1; x < prop.size(); x++) {
            String pciName = prop.getProperty("pci" + x);
            PCI    pci     = (PCI) Class.forName(pciName).newInstance();
            mb.usePCI(pci);
        }
        fis.close();
    }
}
