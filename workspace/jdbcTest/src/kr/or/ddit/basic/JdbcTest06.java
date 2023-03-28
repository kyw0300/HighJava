package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

/*
	회원을 관리하는 프로그램을 작성하시오.
	(MYMEMBER 테이블 이용)
	
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	메뉴 예시)
	-----------------
	1. 자료 추가			--> insert(C)
	2. 자료 삭제			--> delete(D)
	3. 자료 수정			--> update(U)
	4. 전체 자료 출력		--> select(R)
	0. 작업 끝.
	-----------------
	
	조건)
	1) 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력 받는다.)
	2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
	3) 자료 수정에서 '회원ID'는 변경되지 않는다.
*/

public class JdbcTest06 {
	Scanner scan;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public JdbcTest06() {
		scan = new Scanner(System.in);
		this.conn = DBUtil3.getConnection();
	}

	public static void main(String[] args) {
		new JdbcTest06().start();
	}

	private void start() {
		System.out.println(" ** 회원 관리 프로그램 **  \n");
		while (true) {
			try {
				int select = displayMenu();
				switch (select) {
				case 1: // 자료추가
					insert();
					break;
				case 2: // 자료삭제
					delete();
					break;
				case 3: // 자료수정
					update(); 
					break;
				case 4: // 전체 자료 출력
					select();
					break;
				case 0: // 작업 끝
					System.out.println();
					System.out.println("프로그램을 이용해주셔서 감사합니다.");
					return;
				default:
					System.out.println("** 번호를 잘못 입력하셨습니다. 다시 입력해주세요. **");
					System.out.println();
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("** 숫자로 다시 입력해주세요. **");
				System.out.println();
				scan.nextLine();
			}
		}
	}

	private int displayMenu() throws InputMismatchException {
		System.out.println("-----------------");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("0. 작업 끝.");
		System.out.println("-----------------");
		System.out.print("메뉴 선택 >> ");
		
		return scan.nextInt();
	}

	private void insert() { // mem_id, mem_pass, mem_name, mem_tel, mem_addr

		try {
			int count = 0;
			String memId = "";
			do {
				System.out.print("아이디 >> ");
				memId = scan.next();

				String sql1 = "select count(*) cnt from mymember where mem_id = ? ";
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("cnt");
				}
				if (count == 1) {
					System.out.println("입력한 아이디 " + memId + "는(은) 중복된 아이디입니다.");
					System.out.println("다른 아이디로 다시 입력하세요...");
					System.out.println();
				}

			} while (count == 1);

			System.out.print("비밀번호 >> ");
			String memPass = scan.next();

			System.out.print("이름 >> ");
			String memName = scan.next();

			System.out.print("전화번호 >> ");
			String memTel = scan.next();

			System.out.print("주소 >> ");
			scan.nextLine();
			String memAddr = scan.nextLine();

			// insert 시작
			String sql2 = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
					+ "values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memName);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAddr);

			int c = pstmt.executeUpdate();
			if (c > 0) {
				System.out.println(memId + "회원 가입 성공! 환영합니다!");
			} else {
				System.out.println(memId + "회원 가입 실패...");
			}
			System.out.println();

		} catch (SQLIntegrityConstraintViolationException e){
			System.out.println("필수 입력 정보입니다!!");
		} catch (SQLException e) {
			
		} finally {
			if(rs!=null) try {rs.close();} catch (SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
//			if(conn!=null) try {conn.close();} catch (SQLException e) {}
		}
	}

	private void delete() {
		try {
			int count = 0;
			String memId = "";
			do {
				System.out.print("삭제할 회원의 아이디를 입력하세요 >> ");
				memId = scan.next();

				String sql1 = "select count(*) cnt from mymember where mem_id = ? ";
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("cnt");
				}
				if (count == 0) {
					System.out.println("입력한 아이디 " + memId + "는(은) 존재하지 않는 아이디입니다.");
					System.out.println("다른 아이디로 다시 입력하세요...");
				}

			} while (count == 0);

			// delete 시작
			String sql2 = "delete from mymember where MEM_ID = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memId);

			int c = pstmt.executeUpdate();
			if (c > 0) {
				System.out.println("회원정보 삭제 완료...");
			} else {
				System.out.println("회원정보 삭제 실패...");
			}
			System.out.println();

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			if(rs!=null) try {rs.close();} catch (SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
//			if(conn!=null) try {conn.close();} catch (SQLException e) {}
		}
	}

	private void update() {
		try {
			int count = 0;
			String memId = "";
			do {
				System.out.print("수정할 회원의 아이디을 입력하세요 >> ");
				memId = scan.next();

				String sql1 = "select count(*) cnt from mymember where mem_id = ? ";
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("cnt");
				}
				if (count == 0) {
					System.out.println("입력한 아이디 " + memId + "는(은) 존재하지 않는 아이디입니다.");
					System.out.println("다른 아이디로 다시 입력하세요...");
				}

			} while (count == 0);
			
			System.out.print("수정할 비밀번호 >> ");
			String memPass = scan.next();

			System.out.print("수정할 이름 >> ");
			String memName = scan.next();

			System.out.print("수정할 전화번호 >> ");
			String memTel = scan.next();

			System.out.print("수정할 주소 >> ");
			scan.nextLine();
			String memAddr = scan.nextLine();

			// update 시작
			String sql2 = "update mymember set MEM_PASS = ?, MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memPass);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			pstmt.setString(5, memId);

			int c = pstmt.executeUpdate();
			if (c > 0) {
				System.out.println("회원정보 수정 완료...");
			} else {
				System.out.println("회원정보 수정 실패...");
			}
			System.out.println();

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			if(rs!=null) try {rs.close();} catch (SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
//			if(conn!=null) try {conn.close();} catch (SQLException e) {}
		}
	}

	private void select() {
		try {
			String sql = "select lpad(MEM_ID,15), lpad(MEM_PASS,15), lpad(MEM_NAME,15), lpad(MEM_TEL,20), lpad(MEM_ADDR,30) from mymember";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.println();
			System.out.println("\t아이디\t\t비밀번호\t이름\t\t전화번호\t\t주소");
			System.out.println("==================================================================================================");
			while (rs.next()) {
				String memId = rs.getString(1);
				String memPass = rs.getString(2);
				String memName = rs.getString(3);
				String memTel = rs.getString(4);
				String memAddr = rs.getString(5);
//				System.out.printf("%10s %10s %10s %15s %20s\n", memId, memPass, memName, memTel, memAddr);
				System.out.println(memId+ memPass+ memName+ memTel+ memAddr);
			}
			System.out.println("==================================================================================================");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch (SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
//			if(conn!=null) try {conn.close();} catch (SQLException e) {}
		}
	}
}
