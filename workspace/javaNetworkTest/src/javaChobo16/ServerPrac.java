package javaChobo16;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPrac {
	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(6666);
			System.out.println("서버 연결 대기중...");
			Socket socket = server.accept();
			
			
			File file = new File("D:/D_Other/서버복사사자/사자.jpg");
			FileOutputStream fout = new FileOutputStream(file);
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			InputStream in = socket.getInputStream();
			int data = 0;
			
			while( (data = in.read()) != -1 ) {
				bout.write(data);
			}
			bout.flush();
			
			in.close();
			bout.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
