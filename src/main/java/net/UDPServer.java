package net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Objects;
import java.util.Scanner;

public class UDPServer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (DatagramSocket ds = new DatagramSocket(8791)) {
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            while (true) {
                ds.receive(dp);
                String ip = dp.getAddress().getHostAddress();
                int port = dp.getPort();
                String text = new String(dp.getData(), 0, dp.getLength());
                System.out.println(ip + ":" + port + " : " + text);
                ds.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(sc).close();
        }
    }
}
