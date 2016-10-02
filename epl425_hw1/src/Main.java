
public class Main {

	public static void main(String[] args) {
		
		float sum = 0;
		float av;
		int n=1;
		int r=100;
	float start[] = null;
		Client t;
		
		for (int i=0;i<n;i++){
			 new   Client(r,n,i,args[0],Integer.parseInt(args[1]),start, null, null, null, null).create();
			
			//t.create();
			//System.out.println("sss"+t.rtt());
			//sum+=t.sum;
			//new   MultiThreadChatClient(i).run();
			// new Thread(new   MultiThreadChatClient(i)).start();
			//mc.create();
		}
		
		
	
	
		av=sum/(n*300);
		//System.out.println("Average RTT "+(av));

	}
}
