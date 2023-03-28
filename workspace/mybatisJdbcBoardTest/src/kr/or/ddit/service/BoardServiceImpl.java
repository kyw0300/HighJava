package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.BoardDaoImpl;
import kr.or.ddit.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private BoardDaoImpl dao;
	
	private static BoardServiceImpl instance;
	
	//생성자에서 dao호출
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		if(instance == null) instance = new BoardServiceImpl();
		return instance;
	}
	
	@Override
	public List<BoardVO> selectAll() {
		return dao.selectAll();
	}
	
	@Override
	public int insertBoard(BoardVO vo) {
		return dao.insertBoard(vo);
	}
	
	@Override
	public BoardVO selectBoardNo(int no) {
		// 게시글 보기에서는 조회수를 증가하는 작업을 같이 수행해야 한다.
		int cnt = dao.cntPlus(no);
		if(cnt==0) {
			return null;
		}
		return dao.selectBoardNo(no);
	}
	
	@Override
	public int getBoardCount(int no) {
		return dao.getBoardCount(no);
	}
	
	@Override
	public int update(BoardVO vo) {
		return dao.update(vo);
	}
	
	@Override
	public int delete(int no) {
		return dao.delete(no);
	}
	
	@Override
	public void cntPlus(int no) {
		dao.cntPlus(no);
	}
	
	@Override
	public List<BoardVO> search(String word) {
		return dao.search(word);
	}
}
