package study.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

public class MergeFiles {

    private static final int SIZE = 1024 * 1024 * 100;

    public static void main(String[] args) throws Exception {
//    String name = "F:\\money.rmvb";
        String cfg = "F:\\money.rmvb";
//    Split(name);
        Merge(cfg);
    }

    public static void Split(String filename) throws Exception {
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[SIZE];
        int len = 0;
        int count = 1;
        while ((len = fis.read(buf)) != -1) {
            FileOutputStream fos = new FileOutputStream(file.getParent() + file.getName() + ".dat" + count++);
            fos.write(buf, 0, len);
            fos.close();
        }
        Properties prop = new Properties();
        prop.setProperty("file_count", "" + (--count));
        prop.store(new FileOutputStream(file), "file_count=count");
        fis.close();
    }

    public static void Merge(String cfg) throws Exception {
        File file = new File(cfg);
        Properties prop = new Properties();
        prop.load(new FileReader(file));
        ArrayList<FileInputStream> al = new ArrayList<>();
        for (int i = 1; i < Integer.parseInt(prop.getProperty("file_count")); i++) {
            al.add(new FileInputStream(file.getParent() + "money.rmvb.dat" + i));
        }
        Enumeration<FileInputStream> en = Collections.enumeration(al);
        SequenceInputStream sis = new SequenceInputStream(en);
        FileOutputStream fos = new FileOutputStream(file.getParent() + "hehe.rmvb");
        byte[] buf = new byte[1024 * 1024 * 100];
        int len = 0;
        while ((len = sis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fos.close();
        sis.close();
    }
}
