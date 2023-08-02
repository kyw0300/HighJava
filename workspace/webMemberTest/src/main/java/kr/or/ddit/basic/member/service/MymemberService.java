package kr.or.ddit.basic.member.service;

import java.util.List;

import kr.or.ddit.basic.member.dao.MymemberDao;
import kr.or.ddit.basic.member.vo.MymemberVO;

public class MymemberService {
	MymemberDao dao;
	
	private static MymemberService instance;
	private MymemberService() {
		dao = MymemberDao.getInstance();
	}
	
	public static MymemberService getInstance() {
		if(instance == null) instance = new MymemberService();
		return instance;
	}
	
	public List<MymemberVO> selectAllMember(){
		return dao.selectAllMember();
	}
	
	public String idCheck(String id) {
		return dao.idCheck(id);
	}
	
	public int insertMember(MymemberVO vo) {
		return dao.insertMember(vo);
	}
	
	public MymemberVO selectDetail(String id) {
		return dao.selectDetail(id);
	}
	
	public int updateInfo(MymemberVO vo) {
		return dao.updateInfo(vo);
	}
	
	public int deleteInfo(String id) {
		return dao.deleteInfo(id);
	}
	
	public String selectPhotoInfo(String id) {
		return dao.selectPhotoInfo(id);
	}
}
