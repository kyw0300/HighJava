package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface IBoardService {
	
	public List<BoardVO> selectAll();

	public int insertBoard(BoardVO vo);
	
	public BoardVO selectBoardNo(int no);
	
	public int getBoardCount(int no);
	
	public int update(BoardVO vo);
	
	public int delete(int no);
	
	public void cntPlus(int no);
	
	public List<BoardVO> search(String word);
}
