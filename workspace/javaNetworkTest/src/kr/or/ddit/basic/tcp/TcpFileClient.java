package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpFileClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		DataOutputStream dout = null;
		
		File file = new File("d:/d_other/사자.jpg");
		if(!file.exists()) {
			System.exit(0);
		}
		FileInputStream fin = new FileInputStream(file);
		
		Socket socket = new Socket("localhost", 7777);
		System.out.println("서버에 연결되었습니다...");
		
//		file.getName();
		
		dout = new DataOutputStream(socket.getOutputStream());
		int data ;
		dout.writeUTF(file.getName());
		while( (data = fin.read()) != -1) {
			dout.write(data) ;
		}
		dout.flush();
		dout.close();
	}
}
