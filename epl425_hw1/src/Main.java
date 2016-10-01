
public class Main {

	public static void main(String[] args) {
		
		float sum = 0;
		float av;
		int n=10;
	
		Client t;
		
		for (int i=0;i<n;i++){
			 t=new   Client(i,args[0],Integer.parseInt(args[1]));
			
			t.create();
			//System.out.println("sss"+t.rtt());
			sum+=t.sum;
			//new   MultiThreadChatClient(i).run();
			// new Thread(new   MultiThreadChatClient(i)).start();
			//mc.create();
		}
		
		
	
	
		av=sum/n/5;
		System.out.println("Average RTT "+(av));

	}
}
