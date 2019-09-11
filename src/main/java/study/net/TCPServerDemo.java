package study.net;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerDemo {

	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(2222);
		while (true){
			
			Socket s = ss.accept();
			InputStream in = s.getInputStream();
			System.out.println(s.getInetAddress().getHostAddress()+" : 已经进入");
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len=in.read(buf))!=-1){
				String str = new String(buf,0,len);
				System.out.println(s.getInetAddress().getHostAddress()+" : "+str);
			}
			s.close();
			ss.close();
		}
	}

}
