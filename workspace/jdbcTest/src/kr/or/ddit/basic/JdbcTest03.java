package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제3) Lprod_id값을 2개를 입력 받아서 두 값중 작은값부터 큰값 사이의 자료들을 출력하시오.
public class JdbcTest03 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("값 2개 입력 >> ");
		int num1 = scan.nextInt();
//		scan.nextLine();
		int num2 = scan.nextInt();
//		System.out.println(num1);
//		System.out.println(num2);
		
		int big = 0;
		int small = 0;
		
//		if(num1 >= num2) {
//			big = num1;
//			small = num2;
//		} else {
//			big = num2;
//			small = num1;
//		}
		
		big = Math.max(num1, num2);
		small = Math.min(num1, num2);
		
		// DB작업용 객체 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KYW95", "java");
			
//			String sql = "select * from lprod where LPROD_ID >= " + small + " and LPROD_ID <= " + big;
			String sql = "select * from lprod where LPROD_ID between " + small + " and " + big;
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("  == SQL문 처리 결과 ==  ");
			while(rs.next()) {
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("LPROD_NM"));
				System.out.println("======================================");
			}
			
			System.out.println("출력 끝...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
	}
}
