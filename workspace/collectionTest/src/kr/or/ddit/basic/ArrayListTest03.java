package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/* 문제) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중 별명의 길이가
 * 		 제일 긴 별명을 출력하시오.
 * 		 (단, 각 별명의 길이는 모두 다르게 입력한다.) 
 */
 
public class ArrayListTest03 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		
		System.out.println("5개의 서로 다른 길이의 별명을 입력하세요~~");
		for(int i=0; i<5; i++) {
			System.out.print((i+1) + "번째 별명 입력: ");
			String name = scanner.nextLine();
			list.set(i, name);
			for(int j=0; j<i; j++) {
				if(list.get(i).length() == list.get(j).length()) {
					System.out.println("별명의 길이를 모두 다르게 입력해야 합니다!!");
					i--;
					break;
				}
			}
		}
		System.out.println();
		System.out.println("전체 별명 : " + list);
		
		int max = 0;
		for(int i=0; i<list.size(); i++) {
			if(max < list.get(i).length()) {
				max = list.get(i).length();
			}
		}
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).length() == max) {
				System.out.println("\n가장 긴 별명 : " + list.get(i));
			}
		}
		
//		String maxList = list.get(0);
//		
//		for(int i=1; i<list.size(); i++) {
//			if(maxList.length() < list.get(i).length()) {
//				maxList = list.get(i);
//			}
//		}
//		System.out.println("제일 긴 별명 : " + maxList);
	}
}
