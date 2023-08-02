package kr.or.ddit.basic.json;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;

public class LprodDaoImpl {
	
	private static LprodDaoImpl instance;
	private LprodDaoImpl() {}
	
	public static LprodDaoImpl getInstance() {
		if(instance == null) instance = new LprodDaoImpl();
		return instance;
	}

	public List<LprodVO> selectAll() {
		SqlSession session = null;
		List<LprodVO> list = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			list = session.selectList("lprod.selectAll");
		} finally {
			session.close();
		}
		
		return list;
	}

}
