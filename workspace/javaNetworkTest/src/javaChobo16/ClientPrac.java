package javaChobo16;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientPrac {
	public static void main(String[] args) {
		Socket socket = null;
		FileInputStream fin = null;
		BufferedInputStream bin = null;
		OutputStream out = null;
		try {
			socket = new Socket("localhost",6666);
			File file = new File("D:/D_Other/사자.jpg");
			fin = new FileInputStream(file);
			bin = new BufferedInputStream(fin);
			
			out = socket.getOutputStream();
			int data = 0;
			while( (data = bin.read()) != -1 ) {
				out.write(data);
			}
			out.flush();
			
			out.close();
			bin.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
