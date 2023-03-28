package chapter12;

public class Sample extends Thread {
	int seq;
	
	public Sample(int seq) {
		this.seq = seq;
	}
	
	public void run() {
		System.out.println(this.seq + " thread start.");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.seq + " thread end.");
	}
	
	public static void main(String[] args) {
//		for(int i=0; i<10; i++) {
//			Thread t = new Sample(i);
//			t.start();
//		}
		Thread t = new Sample(1);
		t.start();
		System.out.println("main end.");
	}
}