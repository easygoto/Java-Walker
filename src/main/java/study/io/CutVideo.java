package study.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CutVideo {

    public static void main(String[] args) throws Exception {

        File                 file   = new File("F:\\123.rmvb");
        BufferedInputStream  bufis  = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bufos  = new BufferedOutputStream(new FileOutputStream("F:\\124.rmvb"));
        int                  offset = (int) (file.length() * 0.3);
        int                  len    = (int) (file.length() * 0.3);
        byte[]               _buf   = new byte[offset];
        byte[]               buf    = new byte[len];
        bufis.read(_buf);
        bufis.read(buf);
        bufos.write(buf);
        bufos.close();
        bufis.close();
    }
}
