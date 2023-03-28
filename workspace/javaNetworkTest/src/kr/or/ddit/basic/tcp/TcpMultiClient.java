package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpMultiClient {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		DataInputStream din = null;
		DataOutputStream dout = null;
		String id = null;
		String flag = null;

		try {
			Socket socket = new Socket("localhost", 7777);
			System.out.print("서버와 연결되었습니다.");
			dout = new DataOutputStream(socket.getOutputStream());
			din = new DataInputStream(socket.getInputStream());
			
			while (true) {
				System.out.print("아이디를 입력해주세요!");
				id = scan.nextLine();

				dout.writeUTF(id);
				
				flag = din.readUTF();
				System.out.println(flag);
				
				if("OK".equals(flag)) {
					break;
				}
			}
			System.out.println("클라이언트 다음 단계");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
