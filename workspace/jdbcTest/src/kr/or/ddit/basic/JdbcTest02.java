package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

// 문제) 사용자로부터 Lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하시오.
public class JdbcTest02 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

//		System.out.print("Lprod_id 값 입력 >> ");
//		int id = scan.nextInt();
		String id = JOptionPane.showInputDialog("Lprod_id 값 입력");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KYW95", "java");
			String sql = "select * from lprod where LPROD_ID >" + id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("입력한 ID값보다 큰 Lprod_id값 출력");
			while(rs.next()) {
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("LPROD_NM"));
				System.out.println("======================================");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
			
		}
	}
}
