package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.util.MybatisSqlSessionFactory;

/*
LPROD 테이블에 새로운 데이터 추가하기

Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
Lprod_id는 현재의 Lprod_id중에서 제일 큰 값보다 1 크게 한다.

입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.


 --> JDBC예제 중 JdbcTest05.java를 myBatis용으로 변경하시오...
 --> mapper 파일명은 'jdbc-mapper.xml'로 한다. 
*/

public class JdbcToMybatis {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// 환경설정
//		Reader rd = null;
//		SqlSessionFactory sqlSessionFactory = null;
//		try {
//			rd = Resources.getResourceAsReader("kr/or/ddit/mybatis/config/mybatis-config.xml");
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
//			
//		} catch (Exception e) {
//			System.out.println("myBatis 초기화 실패!");
//			e.printStackTrace();
//		} finally {
//			if (rd != null) try {rd.close();} catch (IOException e) {}
//		}
		
		SqlSession session = null;
		
		try {
//			session = sqlSessionFactory.openSession();
			session = MybatisSqlSessionFactory.getSqlSession();
			
			// lprod_gu값을 입력 받고 중복되면 다시 입력 받기
			String lprodGu;
			int count = 0;
			while(true) { // do while문으로도 가능
				System.out.print("lprod_gu 입력 >> ");
				lprodGu = scan.next();
				count = session.selectOne("jdbc.cnt", lprodGu);
				if(count == 1) {
					System.out.println();
					System.out.println("입력한 상품 분류 코드 '" + lprodGu + "'는(은) 이미 등록된 코드입니다.");
					System.out.println("다른 코드로 다시 입력하세요...");
				} else {
					break;
				}
			}
			
			// 제일 큰 값보다 1 큰 값 구하기
			int nextId = session.selectOne("jdbc.getLprodId");
			
			System.out.println();
			System.out.println("입력한 상품 분류 코드 '" + lprodGu + "'는(은) 사용 가능한 코드입니다!!");
			System.out.print("Lprod_nm 입력 >> ");
			String lprodNm = scan.next();
			System.out.println();
			
			// 입력한 데이터를 VO에 저장한다.
			LprodVO lprodVo = new LprodVO();
			lprodVo.setLprod_id(nextId);
			lprodVo.setLprod_gu(lprodGu);
			lprodVo.setLprod_nm(lprodNm);
			
			int insertCnt = session.insert("jdbc.insertLprod", lprodVo);
			
			if(insertCnt > 0) {
				System.out.println("insert 작업 성공!!!");
			} else {
				System.out.println("insert 작업 실패!!!");
			}
		} finally {
			session.commit();
			session.close();
		}
		scan.close();
	}
}
