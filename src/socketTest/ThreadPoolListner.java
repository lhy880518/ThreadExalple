package socketTest;

import java.net.Socket;

public class ThreadPoolListner implements Runnable{
	Socket socket = null;
	
	//�ʱ�ȭ �۾����� ����
	public ThreadPoolListner(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("������ ����� IP�� "+socket.getInetAddress()+" �Դϴ�.");
		
	}

}
