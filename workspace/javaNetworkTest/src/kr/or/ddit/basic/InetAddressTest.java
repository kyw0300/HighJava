package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 ==> IP주소를 다루기 위한 클래스
		
		// www.naver.com 의 IP정보 가져오기
		InetAddress ip = InetAddress.getByName("www.w3school.com");
				
		System.out.println("HostName : " + ip.getHostName());
		System.out.println("HostAddress : " + ip.getHostAddress());
		System.out.println("toString : " + ip.toString());
		System.out.println();
		
		// 자신 컴퓨터의 IP정보 가져오기
		InetAddress localIP = InetAddress.getLocalHost();
		System.out.println("HostName : " + localIP.getHostName());
		System.out.println("HostAddress : " + localIP.getHostAddress());
		System.out.println();
		
		// IP주소가 여러 개인 호스트의 정보 가져오기
		InetAddress[] ipArr = InetAddress.getAllByName("google.com");
		for(InetAddress tempIp : ipArr) {
			System.out.println(tempIp.toString());
		}
	}
}
