package kr.or.ddit.basic.session;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDao {
	private static MemberDao instance;
	private MemberDao() {}
	
	public static MemberDao getInstance() {
		if(instance == null) instance = new MemberDao();
		return instance;
	}
	
	public MemberVO getMember(MemberVO vo) {
		 SqlSession session = null;
		 MemberVO loginMemberVo = null; // 반환값이 저장될 변수
		 
		 try {
			session = MybatisSqlSessionFactory.getSqlSession();
			loginMemberVo = session.selectOne("member.getMember",vo);
		} catch (Exception e) {

		} finally {
			session.close(); // 세션 닫기
		}
		 
		return loginMemberVo;
	}
}
