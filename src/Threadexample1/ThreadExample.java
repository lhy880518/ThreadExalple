package Threadexample1;

public class ThreadExample extends Thread{
	int count = 0;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread start");
		
		try {
			while(count < 5){
				Thread.sleep(500);
				System.out.println("In Thread, count is " + count);
				count++;
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println("InterruptedException");
		}
		System.out.println("Thread terminate");
	}
	
	public static void main(String[] args) {
		ThreadExample threadExample = new ThreadExample();
		threadExample.start();
		
	}
}
