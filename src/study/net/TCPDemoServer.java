package study.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPDemoServer {

  public static void main(String[] args) throws Throwable {

    ServerSocket ss = new ServerSocket(12345);
    Socket s = ss.accept();
    String ip = s.getInetAddress().getHostAddress();
    BufferedReader bufr = new BufferedReader(new InputStreamReader(s.getInputStream()));
    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
    String line = null;
    while((line=bufr.readLine())!=null){
      out.println(line.toUpperCase());
      System.out.println(ip+": "+line.toUpperCase());
    }
    s.close();
    ss.close();
  }
}
