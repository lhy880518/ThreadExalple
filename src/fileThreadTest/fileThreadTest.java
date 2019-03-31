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
		//���� ���� ��ġ ����
		Path path = Paths.get("C:\\Users\\admin\\Documents\\workspace-sts-3.8.3.RELEASE\\fileThreadTest\\src\\recv");
		//WatchService ����� ���� ���� �� ����
		WatchService watchService = FileSystems.getDefault().newWatchService();
		//������ START��Ű�鼭 RUN�� ȣ�� �� ������ �ο��� ���� ���� RUNNABLE ����
		MyWatchService myWatchService = new MyWatchService(watchService);
		
		//�ش���ġ�� ���ȭ�� ������ ������ register�� ���
		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		
		//������ �����ϸ鼭 RUNNABLE�� ���ڰ����� ����
		Thread th = new Thread(myWatchService, "myWatchService");
		
		//������ ���� = run�Լ� �����
		th.start();
		
		th.join();
		
	}
	
	private static class MyWatchService implements Runnable{
		//������ �ο��� ���� ����
		WatchService watchService;
		
		//�̺κ��� �������� �����ϱ����� �κ�
		public MyWatchService(WatchService watchService) {
			// TODO Auto-generated constructor stub
			this.watchService = watchService;
		}
		
		@Override
		//������ start�ϰԵǸ� run�� �ڵ�����
		public void run() {
			// TODO Auto-generated method stub
			try {
				//take������� �ش� ��ġ�� ���� �̺�Ʈ�� ���涧 ���� ���
				WatchKey key = watchService.take();
				while(key != null){
					//�뷮�ǿ� ���ؼ� ������ �����ɼ� �����Ƿ� ����Ʈ�� ����
					List<WatchEvent<?>> list = key.pollEvents();
					
					//�̺�Ʈ�� �߻��Ͽ����� for������ ����Ʈ ����ŭ �����鼭 ó���� �۾��� ó����
					for(WatchEvent<?> watchEvent : list)
					{
						Kind<?> kind = watchEvent.kind();
						Path path = (Path) watchEvent.context();
						
						//���� ������ ã������ ������ �����϶��� ó��
						if(kind == StandardWatchEventKinds.ENTRY_CREATE)
						{
							System.out.println("������ ���ϸ� = "+path.getFileName());
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
