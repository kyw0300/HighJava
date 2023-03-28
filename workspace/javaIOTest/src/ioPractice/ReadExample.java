package ioPractice;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class ReadExample {
	public static void main(String[] args) {
//		try {
//			InputStream is = new FileInputStream("c:/temp/test3.db");
//			ByteArrayOutputStream os = new ByteArrayOutputStream();
//			int data;
//			while((data=is.read()) != -1) {
////				int data = is.read();
////				if(data == -1) break;
////				System.out.println(data);
//				os.write(data);
//			}
//			byte[] output = os.toByteArray();
//			System.out.println(Arrays.toString(output));
//			is.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			InputStream is = new FileInputStream("c:/temp/test2.db");
			byte[] buffer = new byte[5];
//			while(true) {
				int readByteNum = is.read(buffer,2,3);
//				if(readByteNum == -1) break;
				for(int i=0; i<buffer.length; i++) {
					System.out.println(buffer[i]);
				}
//			}
			is.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
