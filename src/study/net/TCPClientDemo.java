package study.net;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TCPClientDemo {

	public static void main(String[] args) throws IOException {

		Socket s = new Socket("192.168.1.109",2222);
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		PrintStream out = new PrintStream(s.getOutputStream(),true);
		String line = null;
		while ((line=bufr.readLine())!=null){
			if ("over".equalsIgnoreCase(line)){
				out.print("已经退出");
				break;
			}
			out.print(line);
			
		}
		s.close();
	}

}
