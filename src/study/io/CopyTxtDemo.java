package study.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyTxtDemo {

  public static void main(String[] args) {

    FileReader fr = null;
    FileWriter fw = null;
    
    try {
      fr = new FileReader("C:\\123.txt");
      fw = new FileWriter("D:\\123.txt");
      char [] buf = new char[1024];
      int len = 0;
      while ((len=fr.read(buf))!=-1){
        fw.write(buf, 0, len);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fw != null){
        try {
          fw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (fr != null){
        try {
          fr.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}