import java.io.BufferedReader;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;
import java.net.ServerSocket;

/*
 * A chat server that delivers public and private messages.
 */
public class Server {
	public static boolean flag=false;
	// The server socket.
	private static ServerSocket serverSocket = null;
	// The client socket.
	private static Socket clientSocket = null;

	// This chat server can accept up to maxClientsCount clients' connections.
	// private static final int maxClientsCount = 10;
	// private static final clientThread[] threads = new
	// clientThread[maxClientsCount];

	public static void main(String args[]) {

		// The default port number.
		int portNumber;
		portNumber = Integer.valueOf(args[0]).intValue();
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		}
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				int i = 0;
				 new clientThread(clientSocket ).start();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}

/*
 * The chat client thread. This client thread opens the input and the output
 * streams for a particular client, ask the client's name, informs all the
 * clients connected to the server about the fact that a new client has joined
 * the chat room, and as long as it receive data, echos that data back to all
 * other clients. When a client leaves the chat room this thread informs also
 * all the clients about that and terminates.
 */
class clientThread extends Thread {
	
	private int id = 0, i=0;
	private BufferedReader is = null;
	private PrintStream os = null;
	private Socket clientSocket = null;
	long cpuTime[]=new long[10];
	public clientThread(Socket clientSocket) {
		// this.id=id;

		this.clientSocket = clientSocket;
		try {
			PrintStream os = new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.threads = threads;
		// maxClientsCount = threads.length;

	}

	public void run() {
		

		try {
			     
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());
			ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
			
float start=System.nanoTime();
float end;
float dur;
int count=0;
			while (true) {
			
				 String line = is.readLine();
			
				 if (!line.startsWith("End")){
					 count++;
					 Random rand = new Random();
					 int myValue = rand.nextInt(870400)+153600;
					 char c[]=new char[myValue];
					 
							os.println("Welcome " +line.substring(6, 8)+" "+String.copyValueOf(c)+" ");
							
				 }
				 else{
					  cpuTime[i] = tmxb.getThreadCpuTime(this.getId());
					 int sum=0;
					 if (i==4){
					 for (int j=0;j<5;j++)
					 {
						 System.out.println("CPUTime: "+j+" "+cpuTime[j]);
					 sum+=cpuTime[j];
					 }
					 System.out.println("Average cpu: "+ sum/10.0);
					 
					 }
					 
					 i++;
					 end=System.nanoTime();
					 dur=end-start;
					 dur=(float)(dur/1000000000.0);
					 	double x=count/dur;
					 	System.out.println("Throughput: "+x);
					 	
						clientSocket.close();
						break;
				 }
							//os.flush();
				
					
				}

			
		} catch (IOException e) {
		}
	}
}
