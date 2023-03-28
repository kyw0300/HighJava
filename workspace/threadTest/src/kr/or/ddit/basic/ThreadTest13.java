package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
	10마리의 말들이 경주하는 프로그램을 작성하기
	
	말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 이 클래스는 
	말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
	그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준을 갖고 있다.
	(Comparable 인터페이스 구현)
	
	경기 구간은 1 ~ 50 구간으로 되어 있다.
	경기가 끝나면 등수 순으로 출력한다.
	
	겅기 중간 중간에 각 말들의 위치를 아래와 같이 출력한다.
	예시)
	말이름01 : ------->------------------------------------------
	말이름02 : ------------->------------------------------------
	말이름03 : ----->--------------------------------------------
	...
	말이름10 : ---------->---------------------------------------
*/

public class ThreadTest13 {
	public static int count = 0;
	public static ArrayList<Horse> horses = new ArrayList<>();

	public static void main(String[] args) {
		HorseRace hhh = new HorseRace();

		horses.add(new Horse("01번"));
		horses.add(new Horse("02번"));
		horses.add(new Horse("03번"));
		horses.add(new Horse("04번"));
		horses.add(new Horse("05번"));
		horses.add(new Horse("06번"));
		horses.add(new Horse("07번"));
		horses.add(new Horse("08번"));
		horses.add(new Horse("09번"));
		horses.add(new Horse("10번"));

		for (Horse hh : horses) {
			hh.start();
		}
		hhh.start();

		for (Horse hh : horses) {
			try {
				hh.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		try {
			hhh.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		Collections.sort(horses);
		
		for (int i = 0; i < horses.size(); i++) {
			if(i==0) {
				System.out.println(" ** 경축 ** ");
			}
			System.out.println(horses.get(i).getRanking() + "등 : " + horses.get(i).gethorseName());
		}
	}
}

class Horse extends Thread implements Comparable<Horse> {
	private String horseName;
	private int ranking;
	private int point = 0;

	// 생성자
	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public String gethorseName() {
		return horseName;
	}

	public void sethorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return ranking + " 등 : " + horseName + "\n";
	}

	@Override
	public void run() {
		Random rnd = new Random();
		for (int i = 1; i <= 50; i++) {
			
			try {
				Thread.sleep(rnd.nextInt(300));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
			point++;
		}
//		System.out.println(name + "말 경주 종료");
		ThreadTest13.count++;
		setRanking(ThreadTest13.count);
	}

	@Override
	public int compareTo(Horse horse) {
		return new Integer(this.ranking).compareTo(horse.ranking);
	}
}

class HorseRace extends Thread {

	@Override
	public void run() {
		boolean go = true;
		while(go) {
			
			if(ThreadTest13.count == ThreadTest13.horses.size()) {
				go = false;
			}
			
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@결승점");
			for (Horse h : ThreadTest13.horses) {
				System.out.print(h.gethorseName() + " ");
				for (int i = 1; i <= h.getPoint() - 1; i++) {
					System.out.print("-");
				}
				System.out.print(">");
				for (int j = 1; j < 50 - h.getPoint(); j++) {
					System.out.print("-");
				}
				System.out.println();
//				if (h.getPoint() == 50) {
//					go = false;
//				}
			}
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@결승점");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}
