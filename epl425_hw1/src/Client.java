

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
	  // The client socket
	  private static Socket clientSocket = null;
	// static MultiThreadChatClient[] mc=new   MultiThreadChatClient[10];
	  // The output stream
	  private static PrintStream os = null;
	  // The input stream
	  private static BufferedReader  is = null;

	  private static BufferedReader inputLine = null;
	  private static boolean closed = false;
	  
	  public  void create() {

	    // The default port.
	    int portNumber = port;
	    // The default host.
	    String host = this.host;
	    
	   // if (args.length < 2) {
	      System.out
	          .println("Usage: java MultiThreadChatClient <host> <portNumber>\n"
	              + "Now using host=" + host + ", portNumber=" + portNumber);
	  //  } else {
	     // host = args[0];
	     // portNumber = Integer.valueOf(args[1]).intValue();
	  //  }

	    /*
	     * Open a socket on a given host and port. Open input and output streams.
	     */
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

	    /*
	     * If everything has been initialized then we want to write some data to the
	     * socket we have opened a connection to on the port portNumber.
	     */
	 
	    //i++;
	   
	    if (clientSocket != null && os != null && is != null) {
	    	
	      // mc[i++]=new   MultiThreadChatClient(id);
		/* Create a thread to read from the server. */
     new Thread(new   Client(id,host,port)).start();
      
		//while (!closed) {
			//i++;
			 for (int j=0;j<300;j++)
		     os.println("Hello "+id+" "+clientSocket.getPort()+" "+clientSocket.getInetAddress());
			 os.println("End");
		  //if (i==300)
			 // break;
      // }
	     
	    }
	    //i++;
	    
	  }

	  /*
	   * Create a thread to read from the server. (non-Javadoc)
	   * 
	   * @see java.lang.Runnable#run()
	   */
	  public void run() {
	    /*
	     * Keep on reading from the socket till we receive "Bye" from the
	     * server. Once we received that then we want to break.
	     */
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


