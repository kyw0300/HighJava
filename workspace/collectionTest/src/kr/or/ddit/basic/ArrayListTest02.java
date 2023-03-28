package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
	문제) 5명의 사람 이름을 입력 받아 ArrayList에 저장한 후에 이 ArrayList에 저장된 이름들 중에
		  '김'씨 성의 이름을 찾아 모두 출력하시오.
		  (단, 입력은 Scanner객체를 이용한다.)
*/

public class ArrayListTest02 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		
		System.out.println("5명의 이름을 입력하세요~");
		for(int i=0; i<5; i++) {
			System.out.print((i+1) + "번째 이름 입력: ");
			String name = scanner.nextLine();
			list.add(name);
		}
		System.out.println();
		
		for(int i=0; i<list.size(); i++) {
			String firstName = list.get(i).substring(0,1);
			if(firstName.equals("김")) {
				System.out.println("김씨 성 출력: " + list.get(i));
				
			}
			
//			if(list.get(i).charAt(0) == '김') {
//				System.out.println("김씨 성 출력: " + list.get(i));
//			}
			
//			if(list.get(i).indexOf("김") == 0) {
//				System.out.println("김씨 성 출력: " + list.get(i));
//			}
			
//			if(list.get(i).startsWith("김")) {
//				System.out.println("김씨 성 출력: " + list.get(i));
//			}
		}
	}
}
