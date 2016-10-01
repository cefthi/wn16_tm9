import java.io.BufferedReader;
import java.io.PrintStream;
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
		

		/*
		 * Open a server socket on the portNumber (default 2222). Note that we
		 * can not choose a port less than 1023 if we are not privileged users
		 * (root).
		 */
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		}

		/*
		 * Create a client socket for each connection and pass it to a new
		 * client thread.
		 */
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				int i = 0;
				// for (i = 0; i < maxClientsCount; i++) {
				// if (threads == null) {
				 new clientThread(clientSocket ).start();
				// break;
				// }

				//if (flag) {
					PrintStream os = new PrintStream(clientSocket.getOutputStream());
					//os.println("Server too busy. Try later.");
					//os.close();
					//clientSocket.close();
				//}
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
	
	private int id = 0;
	private BufferedReader is = null;
	private PrintStream os = null;
	private Socket clientSocket = null;
	private  clientThread threads;
	private int maxClientsCount;

	public clientThread(Socket clientSocket) {
		// this.id=id;

		this.clientSocket = clientSocket;
		//this.threads = threads;
		// maxClientsCount = threads.length;

	}

	public void run() {
		int maxClientsCount = this.maxClientsCount;
		// clientThread[] threads = this.threads;

		try {
			/*
			 * Create input and output streams for this client.
			 */         
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());
			//os.println("Welcome"+id);
			// String name = is.readLine().trim();
			// os.println("Hello " + name
			// + " to our chat room.\nTo leave enter /quit in a new line");

			if (threads != null && threads != this) {
				// id++;
				// threads[i].os.println("*** A new user " + name
				// + " entered the chat room !!! ***");
			}

			while (true) {
			//i++;
				 String line = is.readLine();
			
				 if (!line.startsWith("End")){
					 Random rand = new Random();
					 int myValue = rand.nextInt(870400)+153600;
					 char c[]=new char[myValue];
					 
							os.println("Welcome " +line.substring(6, 8)+" "+String.copyValueOf(c)+" ");
							
				 }
				 else{
					
						clientSocket.close();
						break;
				 }
							//os.flush();
				
					
				}

			
		} catch (IOException e) {
		}
	}
}
