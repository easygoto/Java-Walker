package study.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class KeyInDemo {

    public static void main(String[] args) throws IOException {

        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));
        String         line = null;
        while ((line = bufr.readLine()) != null) {
            if (line.equals("over")) {
                break;
            }
            bufw.write(line);
            bufw.newLine();
            bufw.flush();
        }
        bufw.close();
        bufr.close();
    }

    public static void method2() throws IOException {
        InputStream in  = System.in;
        int         len;
        byte[]      buf = new byte[1024];
        while ((len = in.read(buf)) != -1) {
            String temp = new String(buf, 0, len);
            if (temp.equals("over\r\n")) {
                break;
            }
            System.out.println(temp.toUpperCase());
        }
    }

    public static void method() throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream   in = System.in;
        int           ch = 0;
        while ((ch = in.read()) != -1) {
            if (ch == '\r') {
                continue;
            }
            if (ch == '\n') {
                String temp = sb.toString();
                if ("over".equals(temp)) {
                    break;
                }
                System.out.println(temp.toUpperCase());
                sb.delete(0, sb.length());
            } else {
                sb.append((char) ch);
            }
        }
    }
}
