package study.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class URLDemo {

  public static void main(String[] args) throws Exception  {

    String urlStr = "http://www.baidu.com/s?wd=xixi";
    URL url = new URL(urlStr);
    BufferedReader bufr = new BufferedReader(new InputStreamReader(url.openStream()));
    String line = null;
    while((line=bufr.readLine())!=null){
      System.out.println(line);
    }
    bufr.close();
  }

}
