package net;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(2222);
            while (true) {
                Socket s = ss.accept();
                String ip = s.getInetAddress().getHostAddress();
                InputStream in = s.getInputStream();
                System.out.println(ip + " : 已经进入");
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) != -1) {
                    String str = new String(buf, 0, len);
                    System.out.println(ip + " : " + str.toUpperCase());
                }
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(ss).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
