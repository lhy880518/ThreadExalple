package socketTest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//여기 프로젝트에서 해볼것
/*1. 스레드풀을 생성
2. 스레드풀을 이용한 소켓통신 시도*/
public class ThreadPoolMain {
	public static void main(String[] args){		
		try {
			//스레드 풀 생성 CPU숫자만큼
			ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			
			//서버 소켓 생성
			ServerSocket serverSocket = null;
			Socket socket = null;
			
			serverSocket = new ServerSocket(5000);
			System.out.println("서버 대기중");
			socket = serverSocket.accept();
			System.out.println("받음");
			
			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			dos.writeUTF("Test Is Good");
			System.out.println("데이터 전송 완료");
			
			ThreadPoolListner threadPoolListner = new ThreadPoolListner(socket);
			Thread th = new Thread(threadPoolListner);
			th.start();
			
			dos.close();
			socket.close();
			executorService.shutdown();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
		
		
		
		
	}
}
