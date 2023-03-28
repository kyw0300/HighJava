package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class TcpMultiServer {
	public static void main(String[] args) {
		DataInputStream din = null;
		DataOutputStream dout = null;
		HashMap<String, Socket> clients = null;
		String id = null;
		String flag = null;
		try {
			ServerSocket server = new ServerSocket(7777);
			System.out.println("서버가 준비중입니다...");

			while (true) {
				Socket socket = server.accept();
				din = new DataInputStream(socket.getInputStream());
				dout = new DataOutputStream(socket.getOutputStream());

				System.out.println("접속된 아이디의 중복검사를 실행합니다.");
				id = din.readUTF();
				if (clients.containsKey(id)) {
					flag = "중복된 아이디입니다!";
					System.out.println(flag);
					dout.writeUTF(flag);
				} else {
					flag = "OK";
					System.out.println(flag);
					dout.writeUTF(flag);
					break;
				}
			}
			
			System.out.println("서버 다음단계");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
