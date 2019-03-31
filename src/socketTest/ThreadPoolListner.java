package socketTest;

import java.net.Socket;

public class ThreadPoolListner implements Runnable{
	Socket socket = null;
	
	//초기화 작업진행 예정
	public ThreadPoolListner(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("접속한 사람의 IP는 "+socket.getInetAddress()+" 입니다.");
		
	}

}
