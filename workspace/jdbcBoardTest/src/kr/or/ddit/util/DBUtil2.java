package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메서드로 구성된 class 작성하기
// 구성된 class 작성하기 (dbinfo.properties 파일의 내용으로 설정하는 방법)

// Properties 객체 이용하기

public class DBUtil2 {
	private static Properties prop; // Properties 객체 변수 선언
	
	static { // static 초기화 블럭, 클래스 처음 읽어들일때 실행됨
		prop = new Properties(); // Properties 객체 변수 선언
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
			
			fin = new FileInputStream(f);
			prop.load(fin);
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("입출력 오류... : 드라이버 로딩 실패!!!");
			e.printStackTrace();
		} finally {
			if(fin!=null) try {fin.close();} catch(IOException e) {}
		}
	}
	
	// Connection 객체를 반환하는 메서드
	public static Connection getConnection() {
		try {
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KYW95", "java");
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));
		} catch (SQLException e) {
			System.out.println("DB연결 실패!!!");
			return null;
		}
	}
}
