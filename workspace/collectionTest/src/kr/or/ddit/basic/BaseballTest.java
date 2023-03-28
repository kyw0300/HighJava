package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/*
	문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
		  컴퓨터의 숫자는 난수를 이용하여 구한다. (1~9사이의 값 3개)
		  스트라이크는 S, 볼은 B로 나타낸다.
	
	예시) 
		  컴퓨터 난수 ==> 9 5 7
	실행예시)
		  숫자입력 >> 3 5 6
		  3 5 6 ==> 1S 0B
		  숫자입력 >> 7 8 9
		  7 8 9 ==> 0S 2B
		  숫자입력 >> 9 7 5
		  9 7 5 ==> 1S 2B
		  숫자입력 >> 9 5 7
		  9 5 7 ==> 3S 0B
		  
		  축하합니다...
		  당신은 4번 만에 맞추셨습니다!
*/

public class BaseballTest {
	public static void main(String[] args) {
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		HashSet<Integer> ranNumSet = new HashSet<>();

		while (ranNumSet.size() < 3) {
			ranNumSet.add(random.nextInt(9) + 1);
		}
		ArrayList<Integer> ranNumList = new ArrayList<>(ranNumSet);
		Collections.shuffle(ranNumList);
		System.out.println(ranNumList);
		int count = 0;
		
//		ArrayList<Integer> game = new ArrayList<>();
//		game.add(0);
//		game.add(0);
//		game.add(0);
		
		while (true) {
			int strike = 0;
			int ball = 0;
			count++;
			System.out.print("숫자 입력>> ");
			int num1 = Integer.parseInt(scanner.next());
			int num2 = Integer.parseInt(scanner.next());
			int num3 = Integer.parseInt(scanner.next());
//			game.set(1, Integer.parseInt(scanner.next()));
//			game.set(2, Integer.parseInt(scanner.next()));
//			game.set(3, Integer.parseInt(scanner.next()));
//			
//			for(int i=0; i<ranNumList.size(); i++) {
//				if(game.get(i) == ranNumList.get(i)) {
//					strike++;
//				} 
//			}
//			for(int i=0; i<ranNumList.size(); i++) {
//				if(ranNumSet.contains(game.get(i))) {
//					ball++;
//				} 
//			}
			
			if(num1 == ranNumList.get(0)) {
				strike++;
			} 
			if(num2 == ranNumList.get(1)) {
				strike++;
			} 
			if(num3 == ranNumList.get(2)) {
				strike++;
			}
			
			if(ranNumSet.contains(num1)) {
				ball++;
			} 
			if(ranNumSet.contains(num2)) {
				ball++;
			} 
			if(ranNumSet.contains(num3)) {
				ball++;
			}
			
			System.out.println(num1 + " " + num2 + " " + num3 + " ==> " + strike + "S " + (ball-strike) + "B");
			
			if(strike == 3) {
				System.out.println("축하합니다...");
				System.out.println("당신은 " + count + "번째 만에 맞췄습니다!!");
				break;
			}
		}
	}
}
