package kr.or.ddit.basic.fileupload.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;
import kr.or.ddit.vo.FileinfoVO;

public class FileinfoDaoImpl implements IFileinfoDao {
	
	private static IFileinfoDao instance;
	private FileinfoDaoImpl() {}
	
	public static IFileinfoDao getInstance() {
		if(instance == null) instance = new FileinfoDaoImpl();
		return instance;
	}
	
	@Override
	public int insertFileinfo(FileinfoVO fileVO) {
		SqlSession session = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("fileinfo.insertFileinfo", fileVO);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public List<FileinfoVO> getAllFileinfo() {
		SqlSession session = null;
		List<FileinfoVO> list = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			list = session.selectList("fileinfo.getAllFileinfo");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public FileinfoVO getFileinfo(int fileNo) {
		SqlSession session = null;
		FileinfoVO vo = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			vo = session.selectOne("fileinfo.getFileinfo", fileNo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return vo;
	}

}
