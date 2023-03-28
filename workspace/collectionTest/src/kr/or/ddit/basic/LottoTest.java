package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class LottoTest {

	public static ArrayList<Integer> lotto() {
		Random random = new Random();
		HashSet<Integer> lottoSet = new HashSet<>();

		while (lottoSet.size() < 6) {
			lottoSet.add(random.nextInt(45) + 1);
		}
		ArrayList<Integer> lottoList = new ArrayList<>(lottoSet);
		return lottoList;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
		System.out.println("==========================");
		System.out.println("Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println(" 1. Lotto 구입");
		System.out.println(" 2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴선택 : ");
		int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 1:
				System.out.println(" Lotto 구입 시작");
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.print("금액 입력 : ");
				int money = Integer.parseInt(scanner.nextLine());
				
				if(money < 1000) {
					System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
					System.out.println();
					break;
				} else if(money >= 101000) {
					System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
					System.out.println();
					break;
				} else {
				System.out.println();
				System.out.println("행운의 로또번호는 다음과 같습니다.");
				
				for (int i = 1; i <= money / 1000; i++) {
					ArrayList<Integer> lottoList = lotto();

					System.out.print("로또번호" + i + " : ");
					for (int j = 0; j < lottoList.size(); j++) {
						if (j != 0) {
							System.out.print("," + lottoList.get(j));
						} else {
							System.out.print(lottoList.get(j));
						}
					}
					System.out.println();
				}
				System.out.println();
				System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + money % 1000 + "원입니다.");
				System.out.println();
				}
				break;
			case 2:
				System.out.println("감사합니다");
				System.exit(0);
				break;
			}
		}
	}
}
