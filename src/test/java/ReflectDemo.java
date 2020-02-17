import org.junit.Test;
import baseapi.reflection.pci.MainBoard;
import baseapi.reflection.pci.PCI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class ReflectDemo {

    @Test
    public void pci() throws Exception {
        MainBoard mb = new MainBoard();
        mb.run();
        Properties prop = new Properties();
        prop.load(new FileReader("config/baseApi/pci.properties"));
        for (int x = 1; x <= prop.size(); x++) {
            String pciName = prop.getProperty("pci" + x);
            PCI pci = (PCI) Class.forName(pciName).newInstance();
            mb.usePCI(pci);
        }
    }
}
