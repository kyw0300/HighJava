package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface IBoardDAO {
	/**
	 * 전체 게시글 정보를 가져와 List에 담아서 반환하는 메서드
	 * @return BoardVO객체를 갖고 있는 List객체
	 */
	public List<BoardVO> selectAll();
	
	/**
	 * 새 글 작성
	 * 
	 * @param vo DB체 insert할 데이터가 저장된 BoardVO 객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertBoard(BoardVO vo);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글 정보를 가져오는 메서드
	 * @param no 가져올 게시글 번호
	 * @return 해당 게시글 자료가 있으면 해당 게시글 정보가 저장된 BoardVO객체, 자료가 없으면 null
	 */
	public BoardVO selectBoardNo(int no);
	
	/**
	 * 글 번호 존재 유무 확인
	 * @param no
	 * @return
	 */
	public int getBoardCount(int no);
	
	/**
	 * 수정할 데이터가 저장된 BoardVO객체를 매개변수로 받아서 수정하는 메서드
	 * @param vo 수정할 데이터가 저장될 BoardVO객체
	 * @return
	 */
	public int update(BoardVO vo);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글 정보를 삭제하는 메서드
	 * @param no 삭제할 게시글 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int delete(int no);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글의 조회수를 증가시키는 메서드
	 * @param no 조회수를 중가시킬 게시글의 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int cntPlus(int no);
	
	/**
	 * 게시글의 제목을 이용하여 게시글을 검색하는 메서드
	 * 
	 * @param word 검색할 게시글 제목
	 * @return 검색된 결과가 저장된 List객체
	 */
	public List<BoardVO> search(String word);
}
