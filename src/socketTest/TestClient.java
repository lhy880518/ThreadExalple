package socketTest;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TestClient {
	public static void main(String[] args) {
		String Serverip = "127.0.0.1";
		System.out.println("서버 접속중 : "+Serverip);
		
		try {
			Socket socket = new Socket(Serverip, 5000);
			
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			System.out.println("서버로 부터 받은 메세지 입니다." + dis.readUTF());
			System.out.println("연결을 종료 합니다.");
			
			dis.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
