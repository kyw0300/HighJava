package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

public class RspTeacher {
	public static boolean inputCheck;

	public static void main(String[] args) {
		GameTimer g = new GameTimer();

		// 난수를 이용하여 컴퓨터의 가위 바위 보 정하기
		String[] data = { "가위", "바위", "보" };
		Random rnd = new Random();
		int index = rnd.nextInt(3); // 0~2 사이의 난수 만들기
		String com = data[index]; // 컴퓨터의 가위 바위 보를 정한다.

		// 사용자의 가위 바위 보 입력 받기
		g.start(); // 카운트 다운 시작
		String man = null;
		do {
			man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요...");
//		} while (!("가위".equals(man) || "바위".equals(man) || "보".equals(man)));
		} while (!"가위".equals(man) && !"바위".equals(man) && !"보".equals(man));
		
		inputCheck = true;

		// 결과를 판정하여 출력하기
		String result = "";
//		if (man.equals(com)) {
//			result = "비겼습니다.";
//		} else if (man.equals("가위") && com.equals("보") || man.equals("바위") && com.equals("가위")
//				|| man.equals("보") && com.equals("바위")) {
//			result = "당신이 이겼습니다.";
//		} else {
//			result = "당신이 졌습니다.";
//		}
		
		switch (man + com) {
		case "가위가위":
		case "바위바위":
		case "보보": result = "비겼습니다."; break;
		case "가위보":
		case "바위가위":
		case "보바위": result = "당신이 이겼습니다."; break;
		default: result = "당신이 졌습니다."; break;
		}
		
		System.out.println("--- 결과 ---");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + man);
		System.out.println("결  과 : " + result);
	}
}

class GameTimer extends Thread {
	@Override
	public void run() {
		System.out.println("카운트 다운 시작...");
		for (int i = 10; i >= 1; i--) {
			if (RspTeacher.inputCheck == true) {
				return;
			}
			System.out.println("남은 시간 : " + i + "초!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // for문 끝...

		System.out.println("--- 결과 ---");
		System.out.println("시간이 초과되어 당신이 졌습니다...");
		System.exit(0);
	}
}