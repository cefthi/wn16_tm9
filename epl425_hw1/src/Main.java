
public class Main {

	public static void main(String[] args) {
		
		int sum = 0,s=0;
		float av;
		for (int i=1;i<=10;i++){
			Client c=new   Client(i,args[0],Integer.parseInt(args[1]),s);
			c.create();
		sum+=c.rtt();
			//new   MultiThreadChatClient(i).run();
			// new Thread(new   MultiThreadChatClient(i)).start();
			//mc.create();
		}
		av=(float) (sum/30000000000.0);
		System.out.println("Average RTT "+(av));

	}

}
