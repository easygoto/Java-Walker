package study.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPDemo {
  public static void main(String[] args){
    try {
      InetAddress[] ips = InetAddress.getAllByName("www.baidu.com");
      for (InetAddress ip : ips){
        System.out.println(ip.getHostAddress());
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }
}
