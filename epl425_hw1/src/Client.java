

	import java.io.DataInputStream;
	import java.io.PrintStream;
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.io.IOException;
	import java.net.Socket;
	import java.net.UnknownHostException;

	public class Client implements Runnable {
		
		
		static String host;
		static int port,id=0;
		static float sumRTT=0;
		public Client(int id,String host, int port,float sumRTT){
			this.id=id;
			this.host=host;
			this.port=port;
			this.sumRTT=sum;
		}
	  private static Socket clientSocket = null;

	  // The output stream
	  private static PrintStream os = null;
	  // The input stream
	  private static BufferedReader  is = null;

	  private static BufferedReader inputLine = null;
	  private static boolean closed = false;
	  static float sum=0;
	 float end[]=new float[300];
	 float start[]=new float[300];
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
     new Thread(new   Client(id,host,port,sum)).start();
      
		//while (!closed) {
			//i++;
    
			 for (int j=0;j<300;j++){
				  start[j]=System.nanoTime();
				 
		     os.println("Hello "+id+" "+clientSocket.getPort()+" "+clientSocket.getInetAddress());}
			 os.println("End");
	
	    }  
	  }
	  public double rtt(){
		  return sum;
	  }
	  public void run() {
	
	    String responseLine;
	    try {
	      while ((responseLine = is.readLine()) != null) {
	        System.out.println(responseLine);
	        
	        if (responseLine.startsWith("Welcome")){
	        	int i=Integer.parseInt(responseLine.substring(8, responseLine.length()-1));
		         end[i]=System.nanoTime();
		     
		         sum+=(end[i]-start[i]);
		      
		        }
	        
	        if (responseLine.indexOf("*** Bye") != -1){
	        	
	        
	        	 // System.out.println("RTT "+sum);
	          break;
	       
	        }
	      }
	      closed = true;
	    } catch (IOException e) {
	      System.err.println("IOException:  " + e);
	    }
	  }
	}


