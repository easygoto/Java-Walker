package study.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BaseTypeDemo {

  public static void main(String[] args) throws IOException {

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    dataOutputStream.writeDouble(2.365);
    dataOutputStream.writeBoolean(false);
    
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
    System.out.println(dataInputStream.readDouble());
    System.out.println(dataInputStream.readBoolean());
    
    dataInputStream.close();
    byteArrayInputStream.close();
    dataOutputStream.close();
    byteArrayOutputStream.close();
  }
}
