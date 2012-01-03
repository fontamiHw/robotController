package robot.moving.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RobotNetwork implements Runnable {

	private BufferedReader in;
	private Thread thread;

	public RobotNetwork(InputStream inputStream) {
		in = new BufferedReader(new InputStreamReader(inputStream));
		thread = new Thread(this, "Thread Imput from Field");
		thread.start();
	}

	
	public void run() {
		int inputLine;
	    try {	    	
			while ((inputLine = in.read()) != 0) {
				System.out.println(inputLine);
			}
		} catch (IOException e) {
		}
	    
	    System.out.println("");
	}

	public void destroy() throws IOException {
		in.close();
	}
}
