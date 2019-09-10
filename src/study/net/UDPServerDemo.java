package study.net;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerDemo {

	public static void main(String[] args) throws IOException {

		DatagramSocket ds = new DatagramSocket(8888);
		byte [] buf = new byte [1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		
		while (true){
			
			ds.receive(dp);
			String text = new String(dp.getData(),0,dp.getLength());
			System.out.println(dp.getSocketAddress()+" : "+text);
			ds.close();
		}
	}

}
