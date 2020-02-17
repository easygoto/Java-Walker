package baseapi.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) {
        try {
            Socket s = new Socket(InetAddress.getLocalHost(), 2222);
            BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream out = new PrintStream(s.getOutputStream(), true);
            String line;
            while ((line = bufr.readLine()) != null) {
                if ("over".equalsIgnoreCase(line)) {
                    out.print("已经退出");
                    break;
                }
                out.print(line);
                String result = bufIn.readLine();
                System.out.println(result);
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
