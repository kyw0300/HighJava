package javaChobo16;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpIpServer5 {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			// 서버소켓을 생성하여 5555번 포트와 결합(bind)시킨다.
			serverSocket = new ServerSocket(5555);
			System.out.println("서버가 준비되었습니다.");
			
			socket = serverSocket.accept();
			
			Sender1 sender1 = new Sender1(socket);
			Receiver1 receiver1 = new Receiver1(socket);
			
			sender1.start();
			receiver1.start();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}

class Sender1 extends Thread {
	Socket socket;
	DataOutputStream out;
	String name;
	
	public Sender1(Socket socket) {
		this.socket = socket;
		try {
			out = new DataOutputStream(socket.getOutputStream());
			name = "[" + socket.getInetAddress() + " : " + socket.getPort() + "]";
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(out != null) {
			try {
				out.writeUTF(name + scan.nextLine());
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
	}
}

class Receiver1 extends Thread {
	Socket socket;
	DataInputStream in;
	
	public Receiver1(Socket socket) {
		this.socket = socket;
		try {
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(in != null) {
			try {
				System.out.println(in.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}