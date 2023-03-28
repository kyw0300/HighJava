package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
		 이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 인수로 받아서
		 초기화 처리를 한다.
		 
		 이 Student객체는 List에 저장하여 관리한다.
		 
		 List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
		 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준도
		 구현하여 정렬된 결과를 출력하시오.(클래스명 : SortByTotal)
		 
		 (등수는 List에 전체 데이터가 추가된 후에 구해서 저장한다.)
*/
public class StudentTest {

	// 등수를 구하는 메서드, 총점 높은 순
	public void setRanking(List<Student> studentList) {
		for (Student std1 : studentList) { // 등수를 구할 기준 데이터를 구하기 위한 반복문
			int rank = 1; // 처음에는 1등으로 초기화 해놓고 시작

			for (Student std2 : studentList) { // 비교 대상을 나타내는 반복문

				// 기준값보다 큰 값을 만나면 rank변수값을 증가 시킨다.
				if (std1.getTotal() < std2.getTotal()) {
					rank++;
				}
			}
			std1.setRank(rank + "등~");
		}
	}

	public static void main(String[] args) {
		StudentTest test = new StudentTest();
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student(3, "김자바", 65, 87, 68));
		studentList.add(new Student(1, "고영우", 47, 72, 66));
		studentList.add(new Student(6, "나나나", 47, 72, 66));
		studentList.add(new Student(5, "다다다", 47, 72, 66));
		studentList.add(new Student(4, "이순신", 99, 38, 57));
		studentList.add(new Student(2, "호랑이", 56, 74, 65));

		System.out.println("정렬 전...");
		for (Student stu : studentList) {
			System.out.println(stu);
		}
		System.out.println("---------------------------------------------");

		Collections.sort(studentList);
		System.out.println("학번 오름차순 정렬 후...");
		for (Student stu : studentList) {
			System.out.println(stu);
		}
		System.out.println("---------------------------------------------");

		Collections.sort(studentList, new SortByTotal());
		System.out.println("총점+이름 내림차순 정렬 후...");
		for (Student stu : studentList) {
			System.out.println(stu);
		}
		System.out.println("---------------------------------------------");

//		for (int i = 1; i <= studentList.size(); i++) {
//			studentList.get(i - 1).setRank("총점 " + i + "등!!");
//		}

		// 등수 구하는 메서드 호출
		test.setRanking(studentList);

		System.out.println("총점+이름 내림차순 정렬 + 등수");
		for (Student stu : studentList) {
			System.out.println(stu);
		}
		System.out.println("---------------------------------------------");

	}
}

class Student implements Comparable<Student> {
	private int studentNum;
	private String name;
	private int korean;
	private int english;
	private int math;
	private int total;
	private String rank;

	public Student(int studentNum, String name, int korean, int english, int math) {
		super();
		this.studentNum = studentNum;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.total = korean + english + math;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorean() {
		return korean;
	}

	public void setKorean(int korean) {
		this.korean = korean;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "StudentTest [studentNum=" + studentNum + ", name=" + name + ", korean=" + korean + ", english="
				+ english + ", math=" + math + ", total=" + total + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student stu) {
		// TODO Auto-generated method stub
		return new Integer(this.getStudentNum()).compareTo(stu.getStudentNum());
	}
}

class SortByTotal implements Comparator<Student> {

	@Override
	public int compare(Student stu1, Student stu2) {

//		if (stu1.getTotal() > stu2.getTotal()) {
//			return -1;
//		} else if (stu1.getTotal() < stu2.getTotal()) {
//			return 1;
//		} else {
//			if (stu1.getName().compareTo(stu2.getName()) > 0) {
//				return 1;
//			} else if (stu1.getName().compareTo(stu2.getName()) < 0) {
//				return -1;
//			} else {
//				return 0;
//			}
//
////			return new String(stu1.getName()).compareTo(stu2.getName());
//		}

//		if(new Integer(stu1.getTotal()).compareTo(stu2.getTotal()) == 0) {
//			return new String(stu1.getName()).compareTo(stu2.getName());
//		}
//		return -1 * new Integer(stu1.getTotal()).compareTo(stu2.getTotal());

		// 선생님 방법
		if (stu1.getTotal() == stu2.getTotal()) {
			return stu1.getName().compareTo(stu2.getName());
		} else {
			return Integer.compare(stu1.getTotal(), stu2.getTotal()) * -1;

		}
	}
}
