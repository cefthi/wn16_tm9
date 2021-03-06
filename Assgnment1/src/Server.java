import java.io.BufferedReader;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;
import java.net.ServerSocket;

public class Server {

	public static boolean flag=false;
	private static ServerSocket serverSocket = null;

	private static Socket clientSocket = null;
	public static void main(String args[]) {

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


class clientThread extends Thread {


	private static int n=10;
	private static int r=300;
	private static int i=0;
	static double cpuTime[]=new double[r];
	static long memory[]=new long[r];
	static float start[]=new float[r];
	static float end[]=new float[r];
	static float count[]=new float[r];
	private BufferedReader is = null;
	private PrintStream os = null;
	private Socket clientSocket = null;
	
	ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
	public clientThread(Socket clientSocket) {
		
		// this.id=id;

		this.clientSocket = clientSocket;
		try {
			PrintStream os = new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void run() {
		try {
			@SuppressWarnings("restriction")
			OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());
			start[i]=System.nanoTime();
			float dur;

			while (true) {

				String line = is.readLine();

				if (!line.startsWith("End")){
					count[i]++;
					Random rand = new Random();
					int myValue = rand.nextInt(870400)+153600;
					char c[]=new char[myValue];

					os.println("Welcome " +line.substring(6, 8)+" "+String.copyValueOf(c)+" ");

				}
				else{
					Runtime rr=Runtime.getRuntime();
					rr.gc();
					memory[i]=(rr.totalMemory()-rr.freeMemory());
					cpuTime[i] =((com.sun.management.OperatingSystemMXBean) operatingSystemMXBean).getSystemCpuLoad();
					end[i]=System.nanoTime();
					System.out.println("i  "+i);
					double sum=0;
					int summem=0;
					float sumthr=0;
					int sumco=0;
					if (i==(n-1)){
						for (int j=0;j<n;j++)
						{
							summem+=memory[j];
							sum+=cpuTime[j];
							sumthr+=((end[j]-start[j])/1000000000.0);
							sumco+=count[j];
						}
						System.out.println("Average memory:"+ (summem/1024.0)+" Kbps");
						double cpuav=(1.0*sum/(r*1.0));
						System.out.println("Average cpu: "+cpuav);
						System.out.println("Throughput: "+(1.0*sumco/(1.0*sumthr)));
					}

					i++;


					clientSocket.close();
					break;
				}
			}
		} catch (IOException e) {
		}
	}
}
