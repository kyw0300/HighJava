package javaChobo16;

import java.io.IOException;
import java.net.Socket;

public class TcpIpClient5 {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 5555);
			System.out.println("서버에 연결되었습니다.");
			
			Sender1 sender1 = new Sender1(socket);
			Receiver1 receiver1 = new Receiver1(socket);
			
			sender1.start();
			receiver1.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
