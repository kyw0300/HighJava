package kr.or.ddit.basic.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.basic.member.vo.MymemberVO;
import kr.or.ddit.basic.util.MybatisSqlSessionFactory;

public class MymemberDao {
	private static MymemberDao instance;
	private MymemberDao() {}
	
	public static MymemberDao getInstance() {
		if(instance == null) instance = new MymemberDao();
		return instance;
	}
	
	
	public List<MymemberVO> selectAllMember(){
		SqlSession session = null;
		List<MymemberVO> list = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			list = session.selectList("mymember.selectAllMember");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public String idCheck(String id) {
		SqlSession session = null;
		String memId = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			memId = session.selectOne("mymember.idCheck", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return memId;
	}
	
	public int insertMember(MymemberVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("mymember.insertMember", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		
		return cnt;
	}
	
	public MymemberVO selectDetail(String id) {
		SqlSession session = null;
		MymemberVO vo = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			vo = session.selectOne("mymember.selectDetail",id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return vo;
	}
	
	public int updateInfo(MymemberVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("mymember.updateInfo", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		
		return cnt;
	}
	
	public int deleteInfo(String id) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.delete("mymember.deleteInfo", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		
		return cnt;
	}
	
	public String selectPhotoInfo(String id) {
		SqlSession session = null;
		String photoInfo = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			photoInfo = session.selectOne("mymember.selectPhotoInfo", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return photoInfo;
	}
}
