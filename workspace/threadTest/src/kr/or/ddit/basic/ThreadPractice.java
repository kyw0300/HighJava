package kr.or.ddit.basic;

public class ThreadPractice {
	public static void main(String[] args) {
		Pp calP = new Pp();
		Cal cal = new Cal();
		cal.setCp(calP);
		PrintP pp = new PrintP(calP);
		
		cal.start();
		pp.start();
	}
}

class Pp {
	private double result;
	private boolean go;

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public boolean getGo() {
		return go;
	}

	public void setGo(boolean go) {
		this.go = go;
	}

}

class Cal extends Thread {
	private Pp cp;

	public void setCp(Pp cp) {
		this.cp = cp;
	}

	@Override
	public void run() {
		/*
		 * 원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 ....) * 4;
		 */
		double sum = 0.0;
		for (int i = 1; i <= 1000000000; i++) {
			if (i % 2 == 0) {
				sum += 1.0 / (2 * i - 1) * (-1);
			} else {
				sum += 1.0 / (2 * i - 1);
			}
		}

//		double sum = 0.0;
//		for(int i=1; i<1000000000; i+=2) {
//			if((i/2)%2 == 0) {
//				sum += 1.0 / i;
//			} else {
//				sum -= 1.0 / i;
//			}
//		}
		cp.setResult(sum * 4);
		cp.setGo(true);
	}
}

class PrintP extends Thread {
	private Pp cp;
	
	public PrintP(Pp cp) {
		this.cp = cp;
	}

	@Override
	public void run() {
		while (true) {
			if (cp.getGo() == true) {
				break;
			}
			yield();
		}
		System.out.println("계산 : " + cp.getResult());
		System.out.println("파이 : " + Math.PI);
	}
}