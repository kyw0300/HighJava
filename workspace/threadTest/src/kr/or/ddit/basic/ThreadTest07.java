package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위 바위 보는 난수를 이용해서 구하고 
	사용자의 가위 바위 보는 showInputDialog() 메서드를 이용하여 입력받는다.
	
	입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
	5초 안에 입력이 없으면 게임에 진것으로 처리한다.
	
	5초 안에 입력이 완료되면 승패를 구해서 출력한다.
	
	결과 예시)
	1) 5초안에 입력하지 못했을 경우
		--- 결과 ---
		시간 초과로 당신이 졌습니다.
		
	2) 5초 안에 입력했을 경우
		--- 결과 ---
		컴퓨터 : 가위
		사용자 : 바위
		결  과 : 당신이 이겼습니다.
*/

public class ThreadTest07 {
	public static void main(String[] args) {
		Thread countDown = new Thread(new CountDown5());
		Thread th1 = new Thread(new inputUser());
		Thread th2 = new Thread(new Game());

		countDown.start();
		th1.start();
		try {
			th1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		th2.start();
	}
}

class inputUser implements Runnable {
	public static boolean inputCheck = false;
	public static String user;

	@Override
	public void run() {
		user = JOptionPane.showInputDialog("가위 바위 보 게임!(시간제한 5초)");
		inputCheck = true;
	}
}

class Game implements Runnable {
	Random ran = new Random();
	String[] rsp = new String[] { "가위", "바위", "보" };
	int ranCom = ran.nextInt(3);
	String com = rsp[ranCom];
	String result;

	@Override
	public void run() {
		if (inputUser.user.equals(com)) {
			result = "비겼습니다.";
		} else if (inputUser.user.equals("가위") && com.equals("보") || inputUser.user.equals("바위") && com.equals("가위")
				|| inputUser.user.equals("보") && com.equals("바위")) {
			result = "당신이 이겼습니다.";
		} else {
			result = "당신이 졌습니다.";
		}
		System.out.println();
		System.out.println("--- 결과 ---");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + inputUser.user);
		System.out.println("결  과 : " + result);
//		System.exit(0);
	}
}

class CountDown5 implements Runnable {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			if (inputUser.inputCheck == true) {
				return;
			}
			System.out.println("남은 시간 : " + i + "초!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (inputUser.inputCheck == true) {
			return;
		}

		System.out.println("--- 결과 ---");
		System.out.println("시간 초과로 당신이 졌습니다.");
		System.exit(0);
	}
}