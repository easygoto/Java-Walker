package study.io;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class ShowLineReader {

    public static void main(String[] args) throws IOException {

        LineNumberReader lnr = new LineNumberReader(new FileReader("D:/imooc.html"));
        String line = null;
        lnr.setLineNumber(100);
        while ((line = lnr.readLine()) != null) {
            System.out.println(lnr.getLineNumber() + " " + line);
        }
        lnr.close();
    }

}
