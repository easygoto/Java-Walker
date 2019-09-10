package study.io;

import java.io.UnsupportedEncodingException;

public class ByteToChar {
  public static void main(String[] args) {
    String text = "ab好你cd谢谢";
    System.out.println(cutString(text, 7));
  }
  public static String cutString(String str, int len){
    try {
      byte [] buf = str.getBytes("utf-8");
      int count = 0;
      for (int x = len-1; x >= 0; x --){
        if (buf[x] < 0){
          count ++;
        } else {
          break;
        }
      }
      if (count % 3 == 0){
        return new String(buf, 0, len, "utf-8");
      } else {
        return new String(buf, 0, len-1, "utf-8");
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return str;
  }
}
