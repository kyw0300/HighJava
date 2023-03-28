package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
	문제) 'd:/d_other'폴더에 있는 '펭귄.jpg'파일을 'd:/d_other/연습용'폴더에
		  '복사본_펭귄.jpg' 파일로 복사하는 프로그램을 작성하시오.
*/

public class FileCopy {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("d:/d_other/펭귄.jpg");
			FileOutputStream fos = new FileOutputStream("d:/d_other/연습용/복사본_펭귄.jpg");

			int data = 0;

			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
