package kr.or.ddit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.vo.BoardVO;

public class BoardDaoImpl implements IBoardDAO {
	
	private static BoardDaoImpl instance;
	private BoardDaoImpl() {}
	
	public static BoardDaoImpl getInstance() {
		if(instance == null) instance = new BoardDaoImpl();
		return instance;
	}
	
	@Override
	public List<BoardVO> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> boardList = null;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select BOARD_NO,BOARD_TITLE,BOARD_WRITER,BOARD_CNT from JDBC_BOARD order by 1 desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<>();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setCnt(rs.getInt(4));
				
				boardList.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close();} catch (SQLException e) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return boardList;
	}
	
	@Override
	public int insertBoard(BoardVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board(BOARD_NO,BOARD_TITLE,BOARD_WRITER,BOARD_DATE,BOARD_CONTENT) "
					+ "values(board_seq.nextval,? ,? ,sysdate,? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return cnt;
	}

	@Override
	public BoardVO selectBoardNo(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO vo = new BoardVO();
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select BOARD_NO,BOARD_TITLE,BOARD_WRITER,BOARD_CONTENT,BOARD_DATE,BOARD_CNT "
					+ "from JDBC_BOARD where BOARD_NO= ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setDate(rs.getString(5));
				vo.setCnt(rs.getInt(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close();} catch (SQLException e) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return vo;
	}
	
	@Override
	public int getBoardCount(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = 0; // 반환값이 저장될 변수 선언
		try {
			conn = DBUtil3.getConnection();
			String sql = "select count(*) cnt from JDBC_BOARD where BOARD_NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close();} catch (SQLException e) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return count;
	}
	
	@Override
	public int update(BoardVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "update JDBC_BOARD set BOARD_TITLE = ?, BOARD_CONTENT = ?, BOARD_DATE=sysdate where BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNo());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return cnt;
	}
	
	@Override
	public int delete(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from JDBC_BOARD where BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return cnt;
	}
	
	@Override
	public int cntPlus(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update JDBC_BOARD set BOARD_CNT = BOARD_CNT+1 where BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return cnt;
	}
	
	@Override
	public List<BoardVO> search(String word) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO vo = null;
		List<BoardVO> searchList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select BOARD_NO,BOARD_TITLE,BOARD_WRITER,BOARD_CNT "
					+ "from JDBC_BOARD where BOARD_TITLE like ? order by 1 desc"; // '%' || ? || '%'
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + word + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setCnt(rs.getInt(4));
				
				searchList.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close();} catch (SQLException e) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return searchList;
	}
}
