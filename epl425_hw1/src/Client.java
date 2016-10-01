

	import java.io.DataInputStream;
	import java.io.PrintStream;
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.io.IOException;
	import java.net.Socket;
	import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

	public class Client implements Runnable {
		
		public  boolean closed = false;
		static String host;
		static int port,id=0;
		static float sumRTT=0;
		public Client(int id,String host, int port,double sum2){
			this.closed=false;
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

		
	    String responseLine;
	    try {
	    	if (clientSocket != null && os != null && is != null) {
	    	     new Thread(new   Client(id,host,port,sum)).start();
	    	      
	    			//while (!closed) {
	    				//i++;
	    	    
	    				 for (int j=0;j<5;j++){
	    					  start[j]=(float) (System.currentTimeMillis()/1000.0);
	    					 System.out.println(System.currentTimeMillis());
	    			     os.println("Hello "+id+" "+clientSocket.getPort()+" "+clientSocket.getInetAddress());
	    			     if (j==4)
	    			    	 os.println("End");
	    			     
	    				 }
	    				 
	    				 
	    				
	    		
	    		    }  
	    	sum=0;
	      while ((responseLine = is.readLine()) != null) {
	         System.out.println(responseLine);
	        
	        if (responseLine.startsWith("Welcome")){
	        	int i=Integer.parseInt(responseLine.substring(8, 8+Integer.toString(id).length()));
	        	
	        	 end[i]= System.currentTimeMillis()/1000;
	        	//System.out.println("start "+start[i]);
	        	
		        
		         sum+=(end[i]-start[i]);
		     		      
		        }
	        
	       
	        	
	      
	      }
	     
	    } catch (IOException e) {
	      System.err.println("IOException:  " + e);
	    }

	    
	  }
	  public double rtt(){
		  //for (int i=0;i<300;i++){
			 // System.out.println("start "+i+" "+start[i]);
			  //System.out.println("end "+i+" "+end[i]);
			  
		  //}
			  //sum+=(end[i]-start[i]);	
		  return sum/5.0;
	  }
	  
	  
	  public void run() {

	  }
	}


