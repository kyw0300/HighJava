package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*문제) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중 별명의 길이가
  		제일 긴 별명을 출력하시오.
 		(단, 각 별명의 길이가 같을 수 있다.)
*/

public class ArrayListTest04 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		
		for(int i=0; i<5; i++) {
			System.out.print((i+1) + "번째 별명 입력: ");
			String name = scanner.nextLine();
			list.add(i, name);
		}
		System.out.println();
		
		int max = 0;
		for(int i=0; i<list.size(); i++) {
			if(max < list.get(i).length()) {
				max = list.get(i).length();
			}
		}
		
		System.out.println("*가장 긴 별명들*");
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).length() == max) {
				System.out.println(list.get(i));
			}
		}
//		System.out.println(list);
	}
}
