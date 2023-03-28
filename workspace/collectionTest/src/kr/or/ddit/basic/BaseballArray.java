package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class BaseballArray {
	public static void main(String[] args) {
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		HashSet<Integer> ranNumSet = new HashSet<>();
		
		System.out.println("숫자 야구 게임 시작!! (1~9까지 숫자 3개 입력)");
		
		while (ranNumSet.size() < 3) {
			ranNumSet.add(random.nextInt(9) + 1);
		}
		ArrayList<Integer> ranNumList = new ArrayList<>(ranNumSet);
		Collections.shuffle(ranNumList);
//		System.out.println(ranNumList);
		int count = 0;
		
		ArrayList<Integer> game = new ArrayList<>();
		game.add(0);
		game.add(0);
		game.add(0);
		
		while (true) {
			int strike = 0;
			int ball = 0;
			count++;
			
			System.out.print("숫자 입력>> ");

			game.set(0, Integer.parseInt(scanner.next()));
			game.set(1, Integer.parseInt(scanner.next()));
			game.set(2, Integer.parseInt(scanner.next()));
			
			for(int i=0; i<ranNumList.size(); i++) {
				if(game.get(i) == ranNumList.get(i)) {
					strike++;
				} 
			}
			for(int i=0; i<ranNumList.size(); i++) {
				if(ranNumSet.contains(game.get(i))) {
					ball++;
				} 
			}
			
			System.out.println(game.get(0) + " " + game.get(1) + " " + game.get(2) + " ==> " + strike + "S " + (ball-strike) + "B");
			
			if(strike == 3) {
				System.out.println("축하합니다...");
				System.out.println("당신은 " + count + "번째 만에 맞췄습니다!!");
				break;
			}
		}
	}
}
