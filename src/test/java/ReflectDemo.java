import org.junit.Test;
import reflection.pci.MainBoard;
import reflection.pci.PCI;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReflectDemo {

    @Test
    public void pci() throws Exception {
        MainBoard mb = new MainBoard();
        mb.run();
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(new File("pci.properties"));
        prop.load(fis);
        for (int x = 1; x <= prop.size(); x++) {
            String pciName = prop.getProperty("pci" + x);
            PCI pci = (PCI) Class.forName(pciName).newInstance();
            mb.usePCI(pci);
        }
        fis.close();
    }
}
