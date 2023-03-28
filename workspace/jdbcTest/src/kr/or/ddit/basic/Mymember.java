package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

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

public class Mymember {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new Mymember().startMember();
	}
	
	// 자원을 반납하는 메서드
	private void disConnect() {
		if(rs!=null) try {rs.close();} catch(SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
		if(conn!=null) try {conn.close();} catch(SQLException e) {}
	}
	
	// 작업을 시작하는 메서드
	public void startMember() {
		while(true) {
			try {
				int choice = displayMenu();
				switch (choice) {
				case 1: // 자료추가
					insertMember();
					break;
				case 2: // 자료삭제
					deleteMember();
					break;
				case 3: // 자료수정
					updateMember();
					break;
				case 4: // 전체 자료 출력
					displayAllMember();
					break;
				case 5: // 자료 수정2
					updateMember2();
					break;
				case 6: // 입력한 항목만 수정하기
					updateMember4();
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
	
	// 메뉴를 출력하고 선택한 작업번호를 반환하는 메서드
	private int displayMenu() throws InputMismatchException {
		System.out.println();
		System.out.println("-----------------");
		System.out.println(" 1. 자 료 추 가");
		System.out.println(" 2. 자 료 삭 제");
		System.out.println(" 3. 자료수정  (전체항목수정)");
		System.out.println(" 4. 전 체 자 료 출 력");
		System.out.println(" 5. 자료수정2 (수정항목선택)");
		System.out.println(" 6. 자료수정3 (입력항목만 수정)");
		System.out.println(" 0. 작 업 끝.");
		System.out.println("-----------------");
		System.out.print("작업 선택 >> ");
		
		return scan.nextInt();
	}
	// 회원 정보를 추가(insert)하는 메서드
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");
		
		String id = null;
		int count = 0;
		// 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력 받는다.)
		do {
			System.out.print("회원ID >> ");
			id = scan.next();
			count = getMemberCount(id);
			if(count > 0) {
				System.out.println(id + "은(는) 이미 등록된 회원ID 입니다.");
				System.out.println("다른 회원ID를 입력하세요.");
			}
		} while(count > 0);
		
		System.out.print("비밀번호 >> ");
		String pass = scan.next();

		System.out.print("회원이름 >> ");
		String name = scan.next();

		System.out.print("전화번호 >> ");
		String tel = scan.next();

		System.out.print("회원주소 >> ");
		scan.nextLine(); // 버퍼 비우기
		String addr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
					+ "values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(id + "회원 정보 추가 완료!!!");
			} else {
				System.out.println(id + "회원 정보 추가 실패~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	// 회원정보를 삭제하는 메서드
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from mymember where MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("회원정보 삭제 완료...");
			} else {
				System.out.println(id + "회원은 없는 회원이거나 삭제 작업에 실패했습니다...");
			}
			System.out.println();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	// 회원 정보를 수정하는 메서드
	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		int count = getMemberCount(id);
		if(count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		System.out.println();
		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.next();
		
		System.out.print("새로운 회원이름 >> ");
		String newName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.next();
		
		scan.nextLine(); // 버퍼 비우기
		System.out.print("새로운 회원주소 >> ");
		String newAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set MEM_PASS = ?, MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? "
					+ "where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPass);
			pstmt.setString(2, newName);
			pstmt.setString(3, newTel);
			pstmt.setString(4, newAddr);
			pstmt.setString(5, id);
			
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(id + "회원 정보 수정 완료...");
			} else {
				System.out.println(id + "회원 정보 수정 실패...");
			}
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void updateMember4() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		int count = getMemberCount(id);
		if(count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		// key값 : 수정할 컬럼명, value값 : 수정할 데이터값
		// 수정할 데이터값이 있을 때만 Map에 추가된다.
		Map<String, String> dataMap = new HashMap<>();
		
		scan.nextLine();
		System.out.println();
		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.nextLine().trim();
		if(!"".equals(newPass)) {
			dataMap.put("mem_pass", newPass);
		}
		
		System.out.print("새로운 회원이름 >> ");
		String newName = scan.nextLine().trim();
		if(!"".equals(newName)) {
			dataMap.put("mem_name", newName);
		}
		
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.nextLine().trim();
		if(!"".equals(newTel)) {
			dataMap.put("mem_tel", newTel);
		}
		
		System.out.print("새로운 회원주소 >> ");
		String newAddr = scan.nextLine().trim();
		if(!"".equals(newAddr)) {
			dataMap.put("mem_addr", newAddr);
		}
		
		try {
			conn = DBUtil.getConnection();
			
			String temp = "";	// SQL문의 set 이후에 수정할 컬럼 설정하는 부분이 저장될 변수
			
//			for(String fieldName : dataMap.keySet()) {
//				if(!"".equals(temp)) {
//					temp += ", ";
//				}
//				temp += fieldName + " = '" + dataMap.get(fieldName) + "'";
//			}
//			
//			String sql = "update mymember set " + temp + " where mem_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
			
			
			// '?' 사용해서 하는 방법
			for(String fieldName : dataMap.keySet()) {
				if(!"".equals(temp)) {
					temp += ", ";
				}
				temp += fieldName + " = ?";
			}
			String sql = "update mymember set " + temp + " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			int num = 1;
			for(String fieldName : dataMap.keySet()) {
			pstmt.setString(num++, dataMap.get(fieldName));
			}
			pstmt.setString(num, id);
			
			
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(id + "회원 정보 수정 완료...");
			} else {
				System.out.println(id + "회원 정보 수정 실패...");
			}
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void updateMember3() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		int count = getMemberCount(id);
		if(count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		scan.nextLine();
		System.out.println();
		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.nextLine();
		
		System.out.print("새로운 회원이름 >> ");
		String newName = scan.nextLine();
		
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.nextLine();
		
		System.out.print("새로운 회원주소 >> ");
		String newAddr = scan.nextLine();
		
//		System.out.println(newPass);
//		System.out.println(newName);
//		System.out.println(newTel);
//		System.out.println(newAddr);
		
		if(!newPass.equals("")) {
			try {
				conn = DBUtil.getConnection();
				String sql = "update mymember set MEM_PASS = ? where mem_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newPass);
				pstmt.setString(2, id);
				
				int cnt = pstmt.executeUpdate();
				if (cnt > 0) {
					System.out.println(id + "회원 비밀번호 수정 완료...");
				} else {
					System.out.println(id + "회원 비밀번호 수정 실패...");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disConnect();
			}
		}
		
		if(!newName.equals("")) {
			try {
				conn = DBUtil.getConnection();
				String sql = "update mymember set MEM_NAME = ? where mem_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newName);
				pstmt.setString(2, id);
				
				int cnt = pstmt.executeUpdate();
				if (cnt > 0) {
					System.out.println(id + "회원 이름 수정 완료...");
				} else {
					System.out.println(id + "회원 이름 수정 실패...");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disConnect();
			}
		}
		
		if(!newTel.equals("")) {
			try {
				conn = DBUtil.getConnection();
				String sql = "update mymember set MEM_TEL = ? where mem_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newTel);
				pstmt.setString(2, id);
				
				int cnt = pstmt.executeUpdate();
				if (cnt > 0) {
					System.out.println(id + "회원 전화번호 수정 완료...");
				} else {
					System.out.println(id + "회원 전화번호 수정 실패...");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disConnect();
			}
		}
		
		if(!newAddr.equals("")) {
			try {
				conn = DBUtil.getConnection();
				String sql = "update mymember set MEM_ADDR = ? where mem_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newAddr);
				pstmt.setString(2, id);
				
				int cnt = pstmt.executeUpdate();
				if (cnt > 0) {
					System.out.println(id + "회원 주소 수정 완료...");
				} else {
					System.out.println(id + "회원 주소 수정 실패...");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disConnect();
			}
		}
	}
	
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		int count = getMemberCount(id);
		if(count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		int num;
		String updateField = null;
		String updateFieldTitle = null;
		do {
			System.out.println("수정할 항목을 선택하세요");
			System.out.println("1.비밀번호 | 2.회원이름 | 3.전화번호 | 4.회원주소 | 0.뒤로가기");
			System.out.print("선택 >> ");
			num = scan.nextInt();
			
			switch (num) {
			case 1: updateField = "mem_pass"; updateFieldTitle = "비밀번호";
				break;
			case 2: updateField = "mem_name"; updateFieldTitle = "회원이름";
				break;
			case 3: updateField = "mem_tel"; updateFieldTitle = "전화번호";
				break;
			case 4: updateField = "mem_addr"; updateFieldTitle = "회원주소";
				break;
			case 0:
				return;
			default:
				System.out.println("메뉴 선택을 다시 입력해주세요.");
			}
			
		} while(num < 1 || num > 4);
		
		scan.nextLine(); // 버퍼 비우기
		System.out.println();
		System.out.print("새로운 " + updateFieldTitle + " >> ");
		String updateData = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set " + updateField + " = ? where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, id);
			
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(id + "수정 작업 완료...");
			} else {
				System.out.println(id + "수정 작업 실패...");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}
	
	// 수정2
	private void updateMemberK() {
		
		while(true) {
			System.out.println("수정할 항목을 선택하세요");
			System.out.println("1.비밀번호 | 2.회원이름 | 3.전화번호 | 4.회원주소 | 0.뒤로가기");
			System.out.print("선택 >> ");
			int choice = scan.nextInt();
			
			switch (choice) {
			case 1:
				updatePass();
				break;
			case 2:
				updateName();
				break;
			case 3:
				updateTel();
				break;
			case 4:
				updateAddr();
				break;
			case 0:
				return;
			default:
				System.out.println("메뉴 선택을 다시 입력해주세요.");
			}
		}
	}
	
	private void updatePass() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		int count = getMemberCount(id);
		if(count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		System.out.println();
		System.out.println("새로운 비밀번호 >> ");
		String newPass = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set MEM_PASS = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPass);
			pstmt.setString(2, id);
			
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(id + "회원 비밀번호 수정 완료...");
			} else {
				System.out.println(id + "회원 비밀번호 수정 실패...");
			}
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void updateName() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		int count = getMemberCount(id);
		if(count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		System.out.println();
		System.out.println("새로운 회원이름 >> ");
		String newName = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set MEM_NAME = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newName);
			pstmt.setString(2, id);
			
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(id + "회원 이름 수정 완료...");
			} else {
				System.out.println(id + "회원 이름 수정 실패...");
			}
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void updateTel() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		int count = getMemberCount(id);
		if(count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		System.out.println();
		System.out.println("새로운 전화번호 >> ");
		String newTel = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set MEM_TEL = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newTel);
			pstmt.setString(2, id);
			
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(id + "회원 전화번호 수정 완료...");
			} else {
				System.out.println(id + "회원 전화번호 수정 실패...");
			}
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void updateAddr() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		int count = getMemberCount(id);
		if(count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		System.out.println();
		System.out.println("새로운 회원주소 >> ");
		String newAddr = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set MEM_ADDR = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newAddr);
			pstmt.setString(2, id);
			
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(id + "회원 주소 수정 완료...");
			} else {
				System.out.println(id + "회원 주소 수정 실패...");
			}
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	//회원ID를 매개변수로 받아서 해당 회원ID의 개수를 반환하는 메서드
	private int getMemberCount(String memId) {
		int count = 0;		// 반환값이 저장될 변수 선언
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return count;
	}
	
	private void displayAllMember() {
		System.out.println();
		System.out.println("==================================================================================================");
		System.out.println("\t아이디\t\t비밀번호\t이름\t\t전화번호\t\t주소");
		System.out.println("==================================================================================================");
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select lpad(MEM_ID,15), lpad(MEM_PASS,15), lpad(MEM_NAME,15),"
					+ " lpad(MEM_TEL,20), lpad(MEM_ADDR,25) from mymember";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			int cnt = 0;
			
			while (rs.next()) {
				cnt++;
				String memId = rs.getString(1);
				String memPass = rs.getString(2);
				String memName = rs.getString(3);
				String memTel = rs.getString(4);
				String memAddr = rs.getString(5);
//				System.out.printf("%10s %10s %10s %15s %20s\n", memId, memPass, memName, memTel, memAddr);
				System.out.println(memId + "\t" + memPass + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			if(cnt == 0) {
				System.out.println("등록된 회원정보가 없습니다...");
			}
			
			System.out.println("==================================================================================================");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
}
