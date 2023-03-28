package javaChobo16;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer4 implements Runnable {
	ServerSocket serverSocket;
	Thread[] threadArr;

	public static void main(String[] args) {
		// 5개의 쓰레드를 생성하는 서버를 생성한다.
		TcpIpServer4 server = new TcpIpServer4(5);
		server.start();
	}

	public TcpIpServer4(int num) {
		try {
			// 서버소켓을 생성하여 5555번 포트와 결합(bind) 시킨다.
			serverSocket = new ServerSocket(5555);
			System.out.println(getTime() + "서버가 준비되었습니다.");

			threadArr = new Thread[num];
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public void start() {
		for (int i = 0; i < threadArr.length; i++) {
			threadArr[i] = new Thread(this);
			threadArr[i].start();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(getTime() + "연결요청을 기다립니다.");
				Socket socket = serverSocket.accept();
				System.out.println(getTime() + socket.getInetAddress() + "로부터 연결요청이 들어왔습니다.");
				
				//소켓의 출력스트림을 얻는다.
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				// 원격 소켓에 데이터를 보낸다.
				dos.writeUTF(" [Notice] Test Message1 from Server.");
				System.out.println(getTime() + "데이터를 전송했습니다.");
				
				dos.close();
				socket.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
	}

	// 현재 시간을 문자열로 반환하는 함수
	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}
}
