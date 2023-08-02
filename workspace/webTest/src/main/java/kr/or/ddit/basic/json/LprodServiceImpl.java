package kr.or.ddit.basic.json;

import java.util.List;

public class LprodServiceImpl {
	
	private LprodDaoImpl dao;
	private static LprodServiceImpl instance;
	
	private LprodServiceImpl() {
		dao = LprodDaoImpl.getInstance();
	}
	
	public static LprodServiceImpl getInstance() {
		if(instance == null) instance = new LprodServiceImpl();
		return instance;
	}

	public List<LprodVO> selectAll() {
		return dao.selectAll();
	}

}
