package study.net;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPClientDemo {

	public static void main(String[] args) throws IOException {

		DatagramSocket ds = new DatagramSocket();
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		
		String line = null;
		while ((line=bufr.readLine())!=null){
			if ("over".equalsIgnoreCase(line)){
				break;
			}
			byte [] buf = line.getBytes();
			DatagramPacket dp = new DatagramPacket(buf, buf.length,new InetSocketAddress("127.0.0.1", 8888));
			ds.send(dp);
		}
		ds.close();
	}

}
