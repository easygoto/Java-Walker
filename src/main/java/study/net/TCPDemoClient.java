package study.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TCPDemoClient {
  public static void main(String[] args) throws Throwable {
    Socket s = new Socket("localhost", 12345);
    BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
    PrintStream out = new PrintStream(s.getOutputStream(), true);
    String line = null;
    while((line=bufr.readLine())!=null){
      if ("over".equals(line)){
        break;
      }
      out.println(line);
      String result = bufIn.readLine();
      System.out.println(result);
    }
    s.close();
  }
}
