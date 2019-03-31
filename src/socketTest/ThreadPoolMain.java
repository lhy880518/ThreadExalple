package socketTest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//���� ������Ʈ���� �غ���
/*1. ������Ǯ�� ����
2. ������Ǯ�� �̿��� ������� �õ�*/
public class ThreadPoolMain {
	public static void main(String[] args){		
		try {
			//������ Ǯ ���� CPU���ڸ�ŭ
			ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			
			//���� ���� ����
			ServerSocket serverSocket = null;
			Socket socket = null;
			
			serverSocket = new ServerSocket(5000);
			System.out.println("���� �����");
			socket = serverSocket.accept();
			System.out.println("����");
			
			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			dos.writeUTF("Test Is Good");
			System.out.println("������ ���� �Ϸ�");
			
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
