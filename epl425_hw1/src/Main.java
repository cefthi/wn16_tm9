
public class Main {

	public static void main(String[] args) {
		
		float sum = 0,s=0;
		float av;
		int n=10;
		
		Client[] t=new Client[n];
		
		
		for (int i=0;i<n;i++){
			 t[i]=new   Client(i,args[0],Integer.parseInt(args[1]),s);
			
			t[i].create();
			
			//new   MultiThreadChatClient(i).run();
			// new Thread(new   MultiThreadChatClient(i)).start();
			//mc.create();
		}
		
		
		for (int i=0;i<n;i++){
			sum+=t[i].rtt();
		}
	
		av=(float) (sum/(n*1.0));
		System.out.println("Average RTT "+(av));

	}
	public static boolean finished(Client t[],int n){
		for(int i=0;i<n;i++){
			if(!t[i].closed){return false;}
		}
		return true;
	
		
	}
}
