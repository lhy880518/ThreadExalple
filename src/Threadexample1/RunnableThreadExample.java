package Threadexample1;

public class RunnableThreadExample implements Runnable {

	public int count = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("runnable thread is start");
		try{
			while(count < 5){
				Thread.sleep(500);
				count++;
			}
		}catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println("InterruptedException");
		}
		System.out.println("runnable terminate");
	}
	
	public static void main(String[] args) {
		RunnableThreadExample instance = new RunnableThreadExample();
		Thread thread = new Thread(instance);
		thread.start();
		
		while (instance.count != 5) {
			try {
				thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}

}
