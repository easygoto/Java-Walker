package gui.tankwar;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author trink
 */
public class Settings {
    private static Properties settings = new Properties();

    static {
        try {
            settings.load(new FileReader("config/tankWar/game.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return settings.getProperty(key);
    }
}
