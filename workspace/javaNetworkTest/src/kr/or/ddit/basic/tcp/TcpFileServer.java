package kr.or.ddit.basic.tcp;

//서버가 준비되고 클라이언트가 접속이 완료되면
//
//클라이언트가 'd:/d_other/펭귄.jpg'파일을 서버로 전송한다.
//
//서버는 클라이언트가 보낸 파일을 받아서 'd:/d_other/연습용'폴더에 저장한다.
//
//--------------------------------------------------------
//클라이언트의 작업 순서
//1. 전송할 파일 정보를 갖는 File객체를 생성한다.
//2. 전송할 파일이 있는지 검사해서 없으면 프로그램을 종료한다.
//3. 전송할 파일이 있으면 Socket객체를 생성해서 서버와 연결한다.
//4. 연결이 완료되면 첫번째로 '파일명'을 서버로 전송한다.
//5. 파일의 내용을 읽기 위한 입력용 스트림 객체를 생성한다.
//6. 파일을 읽어서 소켓으로 출력하는 작업을 파일의 모든 내용이 
//   전부 전송될 때까지 반복한다.
//7. 전송이 완료되면 작업 끝...
//
//----------------------------------------------------------
//서버의 작업 순서
//1. 파일이 저장될 폴더 정보를 갖는 File객체를 생성한다.
//2. 해당 폴더가 없으면 새로 생성한다.
//3. ServerSocket객체를 생성한 후 클라이언트의 접속을 기다린다.
//4. 접속이 완료되면 클라이언트가 첫번째로 보낸 '파일명'을 받는다.
//5. 1번의 File객체와 전송 받은 '파일명'을 조합하여 저장할 파일 정보를 갖는
//   File객체를 생성한다.
//6. 저장할 파일 정보를 이용한 파일 출력용 스트림 객체를 생성한다.
//7. 클라이언트가 보낸 파일 데이터를 소켓에서 읽어서 파일로 출력하는 작업을
//   수신 받은 내용이 전부 저장될 때가지 반복한다.
//8. 저장이 완료되면 작업 끝...

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	public static void main(String[] args) throws IOException {
		
		DataInputStream din;
		String name = null;
		
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 준비 중입니다...");
		Socket socket = server.accept();
		
		din = new DataInputStream(socket.getInputStream());
		
		File file = new File("d:/d_other/서버복사");
		if(!file.exists()) {
			file.mkdirs();
		}
		String fileName = din.readUTF();
		File fileJpg = new File(file, fileName);
		
		
		FileOutputStream fout = new FileOutputStream(fileJpg);
		
		
		
		
		int data;
		
		while( (data =din.read()) != -1) {
			fout.write(data);
				
		}
		fout.flush();
		fout.close();
		
		System.out.println("파일 전송 완료!");
		
	}
}
