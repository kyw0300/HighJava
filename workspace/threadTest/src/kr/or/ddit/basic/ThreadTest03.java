package kr.or.ddit.basic;

import java.util.Iterator;

public class ThreadTest03 {
	public static void main(String[] args) {
		// Thread가 실행되는 시간 체크해보기
		Thread th = new Thread(new MyRunner2());
		
		// 1970년 01월 01일 0시 0분 0초(표준시간)로부터 System.currentTimeMillis() 메서드가
		// 실행된 시점까지의 경과한 시간을 밀리세컨드(1/1000초) 단위로 반환한다.
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {th.join(); // 현재 위치에서 대상이 되는 쓰레드(변수 th 쓰레드)가 끝날 때까지 기다린다.
		} catch (InterruptedException e) {}
		
		long endTime = System.currentTimeMillis();
		System.out.println("경과 시간 : " + (endTime - startTime));
	}
}

class MyRunner2 implements Runnable {
	@Override
	public void run() {
		long sum = 0L;
		for(long i=1L; i<=1_000_000_000L; i++) { // 숫자에 '_' 가능
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}
