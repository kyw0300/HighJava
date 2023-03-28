package kr.or.ddit.basic;

import java.util.Arrays;
import java.util.Random;

public class HorseTeacher {
	public static void main(String[] args) {
		HorseGame[] horseArr = new HorseGame[] {
			new HorseGame("01번말"),new HorseGame("02번말"),new HorseGame("03번말"),	
			new HorseGame("04번말"),new HorseGame("05번말"),new HorseGame("06번말"),	
			new HorseGame("07번말"),new HorseGame("08번말"),new HorseGame("09번말"),new HorseGame("10번말"),	
		};
		GameState gs = new GameState(horseArr);
		
		for(HorseGame h : horseArr) {
			h.start();
		}
		gs.start(); // 말들의 현재 위치를 출력하는 쓰레드 시작
		
		for(HorseGame h : horseArr) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		try {
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println();
		System.out.println("경기 끝...");
		System.out.println();
		
		
		// 등수의 오름차순으로 정렬하기
		Arrays.sort(horseArr); // 배열 정렬
		
		for(HorseGame h : horseArr) {
			System.out.println(h);
		}
		
	}
}

class HorseGame extends Thread implements Comparable<HorseGame> {
	public static int currentRank = 0;  // 말의 등수를 구할 때 사용할 변수
	private String horseName;   		// 말이름
	private int rank;	   				// 등수
	private int location;  				// 현재 위치
	
	//생성자
	public HorseGame(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "는(은) "+ rank + "등 입니다.";
	}
	
	// 등수의 오름차순 정렬의 내부 정렬 기준
	@Override
	public int compareTo(HorseGame horse) {
		return Integer.compare(this.rank, horse.getRank());
	}
	
	@Override
	public void run() {
		Random rnd = new Random();
		for(int i=1; i<=50; i++) {
			this.location = i;
			try {
				Thread.sleep(rnd.nextInt(500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		// 한 마리의 말의 경주가 끝나면 현재의 등수를 구해서 저장한다.
		currentRank++;
		this.rank = currentRank;
	}
}

class GameState extends Thread {
	private HorseGame[] horseArr; // 경주에 참가한 말들의 정보가 저장될 배열 변수 선언

	public GameState(HorseGame[] horseArr) {
		super();
		this.horseArr = horseArr;
	}
	
	@Override
	public void run() {
		while(true) {
			
			if(HorseGame.currentRank == this.horseArr.length) { // 모든 말의 경주가 끝났는지 여부 검사
				break;
			}
			
			for(int i=1; i<=10; i++) {
				System.out.println();
			}
			
			for(int i=0; i<horseArr.length; i++) {
				System.out.print(horseArr[i].getHorseName() + " : ");
				
				for(int j=1; j<=50; j++) {
					if(horseArr[i].getLocation() == j) { // 말의 현재 위치 찾기
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println(); 
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}