package core.toolkit;

import java.io.*;

public class FileUtil {

    public static void close(BufferedInputStream bufin) {
        try {
            if (bufin != null) {
                bufin.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(BufferedOutputStream bufout) {
        try {
            if (bufout != null) {
                bufout.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(BufferedReader bufr) {
        try {
            if (bufr != null) {
                bufr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(BufferedWriter bufw) {
        try {
            if (bufw != null) {
                bufw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
