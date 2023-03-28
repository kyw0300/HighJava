package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메서드로 구성된 class 작성하기
public class DBUtil {
	static { // static 초기화 블럭, 클래스 처음 읽어들일때 실행됨
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}
	
	// Connection 객체를 반환하는 메서드
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KYW95", "java");
		} catch (SQLException e) {
			System.out.println("DB연결 실패!!!");
			return null;
		}
	}
}
