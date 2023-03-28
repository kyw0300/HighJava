package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;


public class MemberServiceImpl implements IMemberService {
	
	
	// 일을 시킬 DAO객체 변수 선언
	private IMemberDao dao;
	// 1번
	private static MemberServiceImpl instance;
	
	// 2번 생성자(접근제한자를 private으로)
	private MemberServiceImpl() {
		// dao = new MemberDaoImpl();
		dao = MemberDaoImpl.getInstance(); // DAO객체 생성
	}
	
	// 3번
	public static MemberServiceImpl getInstance() {
		if(instance == null) {
			instance = new MemberServiceImpl();
		}
		return instance;
	}
	
	@Override
	public int insertMember(MemberVO memVO) {
		return dao.insertMember(memVO);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVO) {
		return dao.updateMember(memVO);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}
	
	@Override
	public int updateMember2(Map<String, String> paramMap) {
		return dao.updateMember2(paramMap);
	}
	
	@Override
	public int updateMember3(Map<String, String> dataMap) {
		return dao.updateMember3(dataMap);
	}
}
