package kr.or.ddit.basic;

// 쓰레드에서 객체를 공통으로 사용하는 예제

/*	
 	원주율을 계산하는 쓰레드와 계산된 원주율을 출력하는 쓰레드가 있다.
 	
 	원주율을 저장하는 객체가 필요하다.
 	이 객체를 두 쓰레드에서 공통으로 사용해서 처리한다.
 */

public class ThreadTest14 {
	public static void main(String[] args) {
		
		// 공통으로 사용할 객체 생성
		ShareData data = new ShareData();
		
		// 쓰레드 객체를 생성하고 공통으로 사용할 객체를 쓰레드에 주입한다.
		CalcPI cp = new CalcPI();
		cp.setSd(data);
		
		printPI pp = new printPI(data);
		
		cp.start();
		pp.start();
	}
}

// 원주율을 관리하는 클래스 (공통으로 사용할 클래스)
class ShareData {
	public double result;			// 계산된 원주율이 저장될 변수
	public boolean isOk = false;	// 계산이 완료되었는지 여부를 나타내는 변수
}

// 원주율을 계산하는 쓰레드 클래스
class CalcPI extends Thread {
	private ShareData sd;
	
	// setter를 이용한 공통 객체 초기화
	public void setSd(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
	/*
		원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 ....) * 4;
	*/	
		double sum = 0.0;
		for(int i=1; i<=1000000000; i++) {
			if(i%2 == 0) {
				sum += 1.0/(2*i-1) * (-1);
			} else {
				sum += 1.0/(2*i-1);
			}
		}
		
//		double sum = 0.0;
//		for(int i=1; i<1000000000; i+=2) {
//			if((i/2)%2 == 0) {
//				sum += 1.0 / i;
//			} else {
//				sum -= 1.0 / i;
//			}
//		}
		sd.result = sum * 4;
		sd.isOk = true;
	}
}

// 계산된 원주율을 출력하는 쓰레드 클래스
class printPI extends Thread {
	private ShareData sd;

	// 공통으로 사용할 객체를 생성자에서 초기화
	public printPI(ShareData sd) {
		super();
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			if(sd.isOk == true) {
				break;
			}
			Thread.yield();
		}
		
		System.out.println();
		System.out.println("결과 : " + sd.result);
		System.out.println(" PI  : " + Math.PI);
	}
}
