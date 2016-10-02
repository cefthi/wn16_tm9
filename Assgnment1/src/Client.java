

import java.io.DataInputStream;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable {

	public  boolean closed = false;
	 String host;
	 int port;
	int id=0;
	static float sumRTT=0;
	 float[] start;
	 static int clock2=0;
	int n;
	 int r;
	public Client(int r,int n,int id,String host, int port,float start[], BufferedReader is, PrintStream os, BufferedReader inputLine,Socket c){
		this.clientSocket=c;
		this.is=is;
		this.os=os;
		this.inputLine=inputLine;
		this.r=r;
		this.start=new float[r];
		this.end=new float[r];
		this.n=n;
		this.closed=false;
		this.id=id;
		this.host=host;
		this.port=port;

	}
	private  Socket clientSocket = null;
	private  PrintStream os = null;
	private  BufferedReader  is = null;
	private  BufferedReader inputLine = null;
	float sum=0;
	 float end[]=new float[r];
	
	public  void create() {
		int portNumber = port;
		String host = this.host;
		System.out.println("Trying to connect to=" + host + ", portNumber=" + portNumber);
		try {
			clientSocket = new Socket(host, portNumber);
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			os = new PrintStream(clientSocket.getOutputStream());
			is = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Host doesnt work " + host);
		} catch (IOException e) {
			System.err.println("Input output error "+ host);
		}

		if (clientSocket != null && os != null && is != null) {
			Client c;
			 new Thread(c=new   Client(r,n,id,host,port,start,is,os,inputLine,clientSocket)).start();
			for (int j=0;j<r;j++){
				c.start[j]=System.nanoTime();
				os.println("Hello "+id+" "+clientSocket.getPort()+" "+clientSocket.getInetAddress());	     
			}
			os.println("End");

		}  




	}
	public void run() {
		int i=0;
		int clock=0;
		String responseLine;
		try {
			sum=0;
			while ((responseLine = is.readLine()) != null) {
				System.out.println(responseLine);
				if (responseLine.startsWith("Welcome")){
					i=Integer.parseInt(responseLine.substring(8, 8+Integer.toString(id).length()));
					this.end[clock]= System.nanoTime();     
					sum+=(float) ((this.end[clock]-this.start[clock])/1000000000.0);
					clock++;
				}clock2++;
			}
			sumRTT+=sum;
			if(clock2==(n*r)){
				System.out.println("AverageRTT "+i+" : "+(sumRTT/(n*r)));
				}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


