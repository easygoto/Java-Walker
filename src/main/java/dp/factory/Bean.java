package dp.factory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author trink
 */
public class Bean {

    public static Object get(String id) {
        Object     object   = null;
        Properties props    = new Properties();
        String     fileName = "src/main/java/com/trink/Factory/BeanFactory/beans.properties";
        try {
            props.load(new BufferedInputStream(new FileInputStream(fileName)));
            String vehicleTypeName = props.getProperty(id);
            object = Class.forName(vehicleTypeName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
