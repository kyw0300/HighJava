package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {
	private final Logger logger = Logger.getLogger(MemberDaoImpl.class);
	
	// 싱글톤
	private static MemberDaoImpl instance;
	private MemberDaoImpl() {}
	
	public static MemberDaoImpl getInstance() {
		if(instance == null) {
			instance = new MemberDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public int insertMember(MemberVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성 성공");
			
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
					+ "values(?, ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVO.getMem_id());
			pstmt.setString(2, memVO.getMem_pass());
			pstmt.setString(3, memVO.getMem_name());
			pstmt.setString(4, memVO.getMem_tel());
			pstmt.setString(5, memVO.getMem_addr());
			
			logger.debug("PreparedStatement객체 생성 성공");
			logger.debug("실행 SQL :" + sql);
			logger.debug("사용 데이터 : [" + memVO.getMem_id() + ", " + memVO.getMem_pass() + ", " + memVO.getMem_name() + ",  "
					+ memVO.getMem_tel() + ", " + memVO.getMem_addr() + "]");

			cnt = pstmt.executeUpdate();
			
			logger.info("쿼리문 실행 성공.");

		} catch (SQLException e) {
			logger.error("insert 작업 실패", e);
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
					logger.info("PreparedStatement객체 반납 성공");
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
					logger.info("Connection객체 반납 성공");
				} catch (SQLException e) {
				}
		}

		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성 성공");
			
			String sql = "delete from mymember where MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			logger.debug("PreparedStatement객체 생성 성공");

			cnt = pstmt.executeUpdate();
			logger.info("delete 쿼리문 실행 성공.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			logger.debug("Connection 객체 생성 완료");
			String sql = "update mymember set MEM_PASS = ?, MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? "
					+ "where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			logger.debug("PreparedStatement 객체 생성 완료");
			
			pstmt.setString(1, memVO.getMem_pass());
			pstmt.setString(2, memVO.getMem_name());
			pstmt.setString(3, memVO.getMem_tel());
			pstmt.setString(4, memVO.getMem_addr());
			pstmt.setString(5, memVO.getMem_id());
			
			logger.debug("update 퀴리 안에 값 세팅 완료");

			cnt = pstmt.executeUpdate();
			logger.debug("쿼리 실행 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
					logger.debug("PreparedStatement 객체 반납 완료");
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
					logger.debug("Connection 객체 반납 완료");
				} catch (SQLException e) {
				}
		}
		return cnt;
	}
	
	// Map의 정보 ==> key값 : 수정할컬럼명(field), 수정할데이터(data), 검색할회원ID(memId)
	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수

		try {
			conn = DBUtil3.getConnection();
			String sql = "update mymember set " + paramMap.get("field") + " = ? where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memId"));

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return cnt;
	}

	@Override
	public int updateMember3(Map<String, String> dataMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수

		try {
			conn = DBUtil3.getConnection();

			String temp = ""; // SQL문의 set 이후에 수정할 컬럼 설정하는 부분이 저장될 변수

			for (String fieldName : dataMap.keySet()) {
				if (!"memId".equals(fieldName)) { // Map의 ket값 중 'memId'는 검색할 ID값에 대한 정보이기 때문에 수정할 컬럼을 설정할 때는 포함하지 않는다.
					if (!"".equals(temp)) {
						temp += ", ";
					}
					temp += fieldName + " = '" + dataMap.get(fieldName) + "'";
				}
			}
			String sql = "update mymember set " + temp + " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dataMap.get("memId"));
			

			// '?' 사용해서 하는 방법
//			for (String fieldName : dataMap.keySet()) {
//				if (!"memId".equals(fieldName)) {
//					if (!"".equals(temp)) {
//						temp += ", ";
//					}
//					temp += fieldName + " = ?";
//				}
//			}
//			String sql = "update mymember set " + temp + " where mem_id = ?";
//			pstmt = conn.prepareStatement(sql);
//
//			int num = 1;
//			for (String fieldName : dataMap.keySet()) {
//			if (!"memId".equals(fieldName)) {			
//				pstmt.setString(num++, dataMap.get(fieldName));
//			}			
//			}
//			pstmt.setString(num, id);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = null;

//		MemberVO vo = new MemberVO();

		try {
			conn = DBUtil3.getConnection();
			String sql = "select lpad(MEM_ID,15), lpad(MEM_PASS,15), lpad(MEM_NAME,15),"
					+ " lpad(MEM_TEL,20), lpad(MEM_ADDR,25) from mymember";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			memList = new ArrayList<>();

			while (rs.next()) {
				MemberVO vo = new MemberVO();

				// ResultSet의 데이터를 꺼내와 VO객체에 셋팅한다.
				String memId = rs.getString(1);
				String memPass = rs.getString(2);
				String memName = rs.getString(3);
				String memTel = rs.getString(4);
				String memAddr = rs.getString(5);

				vo.setMem_id(memId);
				vo.setMem_pass(memPass);
				vo.setMem_name(memName);
				vo.setMem_tel(memTel);
				vo.setMem_addr(memAddr);

				memList.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = 0; // 반환값이 저장될 변수 선언
		try {
			conn = DBUtil3.getConnection();
			String sql = "select count(*) cnt from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return count;
	}

}
