package ioPractice;

import java.util.Arrays;

public class Stringprac {
	public static void main(String[] args) {
		String aa = "무궁화 꽃이 피.었,습.니,다~";
		
		for(int i=0; i<aa.length(); i++) {
			System.out.print(aa.charAt(i));
		}
		System.out.println();
		for(int i=0; i<aa.length(); i++) {
			System.out.print(aa.toCharArray()[i]);
		}
		System.out.println();
		System.out.println(aa.length());
		System.out.println(aa.toCharArray().length);
	}
}
