package study;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class IODemo {

    @Test
    public void baseType() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeDouble(2.365);
        dataOutputStream.writeBoolean(false);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        System.out.println(dataInputStream.readDouble());
        System.out.println(dataInputStream.readBoolean());

        dataInputStream.close();
        byteArrayInputStream.close();
        dataOutputStream.close();
        byteArrayOutputStream.close();
    }

    @Test
    public void byteToChar() {
        int len = 7;
        String text = "ab好你cd谢谢";
        System.out.println(byteToCharCutString(text, len));
    }

    private String byteToCharCutString(String str, int len) {
        byte[] buf = str.getBytes(StandardCharsets.UTF_8);
        int count = 0;
        for (int x = len - 1; x >= 0; x--) {
            if (buf[x] < 0) {
                count++;
            } else {
                break;
            }
        }
        if (count % 3 == 0) {
            return new String(buf, 0, len, StandardCharsets.UTF_8);
        }
        return new String(buf, 0, len - 1, StandardCharsets.UTF_8);
    }

    @Test
    public void copyTxt() throws IOException {
        FileReader fr = null;
        FileWriter fw = null;

        try {
            fr = new FileReader("C:\\123.txt");
            fw = new FileWriter("D:\\123.txt");
            char[] buf = new char[1024];
            int len = 0;
            while ((len = fr.read(buf)) != -1) {
                fw.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(fw).close();
            Objects.requireNonNull(fr).close();
        }
    }

    @Test
    public void cutVideo() throws IOException {
        File file = new File("C:\\123.rmvb");
        BufferedInputStream bufis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream("C:\\124.rmvb"));
        int offset = (int) (file.length() * 0.3);
        int len = (int) (file.length() * 0.3);
        byte[] _buf = new byte[offset];
        byte[] buf = new byte[len];
        System.out.println(bufis.read(_buf));
        System.out.println(bufis.read(buf));
        bufos.write(buf);
        bufos.close();
        bufis.close();
    }

    @Test
    public void splitFile() throws IOException {
        final int SIZE = 1024 * 1024 * 100;
        String filename = "C:\\money.rmvb";
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

    public void mergeFiles() throws IOException {
        String filename = "C:\\money.rmvb";
        File file = new File(filename);
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
