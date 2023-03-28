package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

// JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메서드로 구성된 class 작성하기
// 구성된 class 작성하기 (dbinfo.properties 파일의 내용으로 설정하는 방법)

// ResourceBundle 객체 이용하기

public class DBUtil3 {
	static final Logger logger = Logger.getLogger(DBUtil3.class);
	
	private static ResourceBundle bundle; // ResourceBundle 객체 변수 선언
	
	static { // static 초기화 블럭, 클래스 처음 읽어들일때 실행됨
		bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		logger.info("ResourceBundle 객체 생성 - dbinfo.properties파일 읽기");
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			logger.debug("DB드라이버 로딩 성공");
			
		} catch (ClassNotFoundException e) {
			logger.error("DB드라이버 로딩 실패", e);
			e.printStackTrace();
		} 
	}
	
	// Connection 객체를 반환하는 메서드
	public static Connection getConnection() {
		Connection conn = null;
		try {
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KYW95", "java");
			conn = DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
			logger.info("DB 연결 성공 - Connection객체 생성");
		} catch (SQLException e) {
			logger.error("DB연결 실패", e);
			return null;
		}
		return conn;
	}
}
