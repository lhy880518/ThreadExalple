package fileThreadTest;


import java.util.List;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class fileThreadTest {
	public static void main(String[] args) throws Exception {
		//생성 폴더 위치 지정
		Path path = Paths.get("C:\\Users\\admin\\Documents\\workspace-sts-3.8.3.RELEASE\\fileThreadTest\\src\\recv");
		//WatchService 사용을 위한 선언 및 생성
		WatchService watchService = FileSystems.getDefault().newWatchService();
		//스레드 START시키면서 RUN을 호출 시 의존성 부여를 위한 생성 RUNNABLE 생성
		MyWatchService myWatchService = new MyWatchService(watchService);
		
		//해당위치에 어떤변화를 감지할 것인지 register에 등록
		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		
		//스레드 생성하면서 RUNNABLE을 인자값으로 생성
		Thread th = new Thread(myWatchService, "myWatchService");
		
		//스레드 시작 = run함수 실행됨
		th.start();
		
		th.join();
		
	}
	
	private static class MyWatchService implements Runnable{
		//의존성 부여를 위한 선언
		WatchService watchService;
		
		//이부분이 의존성을 주입하기위한 부분
		public MyWatchService(WatchService watchService) {
			// TODO Auto-generated constructor stub
			this.watchService = watchService;
		}
		
		@Override
		//스레드 start하게되면 run은 자동실행
		public void run() {
			// TODO Auto-generated method stub
			try {
				//take선언순간 해당 위치에 파일 이벤트가 생길때 까지 대기
				WatchKey key = watchService.take();
				while(key != null){
					//대량건에 대해서 파일이 생성될수 있으므로 리스트로 선언
					List<WatchEvent<?>> list = key.pollEvents();
					
					//이벤트가 발생하였으니 for문으로 리스트 수만큼 돌리면서 처리할 작업을 처리함
					for(WatchEvent<?> watchEvent : list)
					{
						Kind<?> kind = watchEvent.kind();
						Path path = (Path) watchEvent.context();
						
						//나는 생성만 찾으려고 했으니 생성일때만 처리
						if(kind == StandardWatchEventKinds.ENTRY_CREATE)
						{
							System.out.println("생성된 파일명 = "+path.getFileName());
						}
					}
					
					Thread.sleep(10000);
					key.reset();
					key = watchService.take();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
