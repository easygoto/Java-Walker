package baseapi.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Objects;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) {
        DatagramSocket ds = null;
        Scanner sc = new Scanner(System.in);
        try {
            ds = new DatagramSocket();
            String line;
            while ((line = sc.nextLine()) != null) {
                if ("over".equalsIgnoreCase(line)) {
                    break;
                }
                byte[] buf = line.getBytes();
                DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress("127.0.0.1", 8791));
                ds.send(dp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(sc).close();
            Objects.requireNonNull(ds).close();
        }
    }
}
