package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import oracle.net.aso.p;

/*
	LPROD 테이블에 새로운 데이터 추가하기
	
	Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
	Lprod_id는 현재의 Lprod_id중에서 제일 큰 값보다 1 크게 한다.
	
	입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
*/

public class JdbcTest05 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KYW95", "java");
			
			conn = DBUtil.getConnection();
			
			String gu;	// 상품 분류 코드가 저장될 변수 선언
			int count = 0;
			
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >>");
				gu = scan.next();
				
				String sql1 = "select count(*) cnt from lprod where lprod_gu= ? ";
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, gu);
				resultSet = pstmt.executeQuery();
				
				if(resultSet.next()) {
					count = resultSet.getInt("cnt");
				}
				if(count == 1) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는(은) 이미 등록된 코드립니다.");
					System.out.println("다른 코드로 다시 입력하세요...");
				}
				
			} while(count ==1);
			
//			while (true) {
//				System.out.print("Lprod_gu(분류코드) 입력 >> ");
//				gu = scan.next();
//
//				String sql1 = "select count(*) from lprod where lprod_gu= ? ";
//				pstmt = conn.prepareStatement(sql1);
//				pstmt.setString(1, gu);
//				resultSet = pstmt.executeQuery();
//
//				resultSet.next();
//				count = resultSet.getInt(1);
//				if (count == 1) {
//					System.out.println("이미 존재하는 분류 코드입니다.");
//					System.out.println();
//				} else {
//					break;
//				}
//			}
			System.out.print("Lprod_nm(상품 분류명) 입력 >> ");
			String nm = scan.next();
			
			String sql2 = "select max(lprod_id)+1 maxid from lprod";
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql2);
			resultSet.next();
//			int id = resultSet.getInt("max(lprod_id)+1"); // alias가 없을 때
//			int id = resultSet.getInt(1);
			int id = resultSet.getInt("maxid");
			
			String sql3 = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
					+ "values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql3); // 앞에서 close() 해주면 경고 없어짐
			pstmt.setInt(1, id);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int c = pstmt.executeUpdate();
			if(c > 0) {
				System.out.println("입력 성공");
			} else {
				System.out.println("입력 실패");
			}

		} catch (SQLException e) {
			// TODO: handle exception
//		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(resultSet!=null) try {resultSet.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
	}
}
