

	import java.io.DataInputStream;
	import java.io.PrintStream;
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.io.IOException;
	import java.net.Socket;
	import java.net.UnknownHostException;

	public class Client implements Runnable {
		static String host;
		static int port;
		static int id=0;
		public Client(int id,String host, int port){
			this.id=id;
			this.host=host;
			this.port=port;
		}
	  private static Socket clientSocket = null;

	  // The output stream
	  private static PrintStream os = null;
	  // The input stream
	  private static BufferedReader  is = null;

	  private static BufferedReader inputLine = null;
	  private static boolean closed = false;
	  
	  public  void create() {
	    int portNumber = port;

	    String host = this.host;
	      System.out.println("Now using host=" + host + ", portNumber=" + portNumber);
	    try {
	      clientSocket = new Socket(host, portNumber);
	      inputLine = new BufferedReader(new InputStreamReader(System.in));
	      os = new PrintStream(clientSocket.getOutputStream());
	      is = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
	    } catch (UnknownHostException e) {
	      System.err.println("Don't know about host " + host);
	    } catch (IOException e) {
	      System.err.println("Couldn't get I/O for the connection to the host "
	          + host);
	    }


	    if (clientSocket != null && os != null && is != null) {
     new Thread(new   Client(id,host,port)).start();
      
		//while (!closed) {
			//i++;
    
			 for (int j=0;j<300;j++)
		     os.println("Hello "+id+" "+clientSocket.getPort()+" "+clientSocket.getInetAddress());
			 os.println("End");
	
	    }  
	  }
	  public void run() {
	
	    String responseLine;
	    try {
	      while ((responseLine = is.readLine()) != null) {
	        System.out.println(responseLine);
	        if (responseLine.indexOf("*** Bye") != -1)
	          break;
	      }
	      closed = true;
	    } catch (IOException e) {
	      System.err.println("IOException:  " + e);
	    }
	  }
	}


