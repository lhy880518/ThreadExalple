package socketTest;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TestClient {
	public static void main(String[] args) {
		String Serverip = "127.0.0.1";
		System.out.println("���� ������ : "+Serverip);
		
		try {
			Socket socket = new Socket(Serverip, 5000);
			
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			System.out.println("������ ���� ���� �޼��� �Դϴ�." + dis.readUTF());
			System.out.println("������ ���� �մϴ�.");
			
			dis.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
