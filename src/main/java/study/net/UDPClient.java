package study.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {

  public static void main(String[] args) {

    DatagramSocket ds = null;
    Scanner sc = null;
    try {
      ds = new DatagramSocket();
      sc = new Scanner(System.in);
      String str = null;
      while((str=sc.nextLine()) != null){
        byte [] buf = str.getBytes();
        DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), 8791);
        ds.send(dp);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      sc.close();
      ds.close();
    }
  }
}
