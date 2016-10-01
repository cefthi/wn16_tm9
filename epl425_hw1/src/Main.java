
public class Main {

	public static void main(String[] args) {
		
		for (int i=0;i<10;i++){
			new   Client(i).create();
		
			//new   MultiThreadChatClient(i).run();
			// new Thread(new   MultiThreadChatClient(i)).start();
			//mc.create();
		}

	}

}
