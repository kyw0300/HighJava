package kr.or.ddit.basic.tcp;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

// 서버에 접속하면 'd:/d_other/펭귄.jpg'파일을 서버로 전송한다.
public class TcpFileClientTDialog {
	
	public static void main(String[] args) {
		new TcpFileClientTDialog().clientStart();
	}
	
	public void clientStart() {
		Socket socket = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		DataOutputStream dout = null;
		
		// 전송할 파일 정보를 갖는 File객체 생성
//		File file = new File("d:/d_other/펭귄.jpg");
		File file = showDialog("OPEN");
		String fileName = file.getName();
		
		if(!file.exists()) {
			System.out.println("전송할 " + fileName + "파일이 없습니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost", 7777);
			
			System.out.println("파일 전송 시작...");
			
			dout = new DataOutputStream(socket.getOutputStream());
			dout.writeUTF(fileName);
			
			// 파일 읽기용 스트림 객체 생성
			bin = new BufferedInputStream(new FileInputStream(file));
			
			// 서버로 전송할 출력용 스트림 객체 생성
			bout = new BufferedOutputStream(socket.getOutputStream());
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			// 파일 내용을 읽어서 서버로 전송한다.
			while( (len = bin.read(temp)) > 0) {
				bout.write(temp, 0, len);
			}
			bout.flush();
			
			System.out.println("파일 전송 완료...");
			
		} catch (Exception e) {
			System.out.println("파일 전송 실패!!!");
			e.printStackTrace();
		} finally {
			if(dout!=null) try {dout.close();} catch(IOException e) {}
			if(bout!=null) try {bout.close();} catch(IOException e) {}
			if(bin!=null) try {bin.close();} catch(IOException e) {}
			if(socket!=null) try {socket.close();} catch(IOException e) {}
		}
	}
	
	// openType은 열기용일 때는 "OPEN", 저장용일 때는 "SAVE"로 지정한다.
		private File showDialog(String openType) {
			// SWING의 파일 열기, 저장 창 연습
			
			JFileChooser chooser = new JFileChooser();
			
			// 선택할 파일의 확장자 설정
			FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word", "doc", "docx");
			FileNameExtensionFilter img = new FileNameExtensionFilter("Image Files", new String[] {"png", "jpg", "gif"});
			FileNameExtensionFilter pdf = new FileNameExtensionFilter("PDF 파일", "pdf");
			FileNameExtensionFilter txt = new FileNameExtensionFilter("text 파일", "txt");
			
			// 구성한 확장자 추가
			chooser.addChoosableFileFilter(doc);
			chooser.addChoosableFileFilter(img);
			chooser.addChoosableFileFilter(pdf);
			chooser.addChoosableFileFilter(txt);
			
			// 구성한 확장자 목록 중에서 현재 선택된 상태가 될 확장자 지정
			// ==> 이것을 설정하지 않으면 첫번째로 추가한 확장자가 기본적으로 선택된다.
//				chooser.setFileFilter(txt);
			
			// 확장자 목록에 '모든 파일' 목록을 표시할지 여부 설정
//			chooser.setAcceptAllFileFilterUsed(true); // 기본값
//			chooser.setAcceptAllFileFilterUsed(false);
			
			//Dialog창이 나타날 때 기본적으로 보여줄 경로 설정하기
			chooser.setCurrentDirectory(new File("d:/d_other"));
			
			// Dialog창 나타내기 (열기용과 저장용을 구분해서 나타낸다.)
//				int result = chooser.showOpenDialog(new Panel()); // 열기용
			
			int result;
			if("OPEN".equals(openType.toUpperCase())) {
				result = chooser.showOpenDialog(new Panel());
			} else if("SAVE".equals(openType.toUpperCase())) {
				result = chooser.showSaveDialog(new Panel());
			} else {
				System.out.println("openType은 'OPEN'또는 'SAVE'로 지정해야 합니다.");
				return null;
			}
			
			File selectFile = null;
			if(result == JFileChooser.APPROVE_OPTION) { // '저장' 또는 '열기'버튼을 눌렀을 경우
				
				// 현재 선택한 파일 정보 구하기
				selectFile = chooser.getSelectedFile();
			}
			
			return selectFile;
		}
}
