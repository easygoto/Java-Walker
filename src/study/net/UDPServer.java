package study.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class UDPServer {

  public static void main(String[] args) {
    DatagramSocket ds = null;
    Scanner sc = null;
    try {
      ds = new DatagramSocket(8791);
      sc = new Scanner(System.in);
      byte [] buf = new byte[1024];
      DatagramPacket dp = new DatagramPacket(buf, buf.length);
      while(true){
        ds.receive(dp);
        String ip = dp.getAddress().getHostAddress();
        int port = dp.getPort();
        String text = new String(dp.getData(),0,dp.getLength());
        System.out.println(ip+":"+port+" : "+text);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      sc.close();
      ds.close();
    }
  }
}
