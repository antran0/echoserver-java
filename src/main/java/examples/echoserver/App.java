package examples.echoserver;

import java.io.IOException;
import java.util.Scanner;

public class App 
{
	static final String HOST = "localhost";
	static final int PORT = 3000;
	
    public static void main( String[] args ) throws IOException
    {
    	EchoClient client = new EchoClient();
    	Scanner scan = new Scanner(System.in);
    	
    	try {
	    	Runnable r = new Runnable() {
	    		public void run() {
	    			EchoServer server = null;
	    			try {
						server = new EchoServer(PORT);
						server.start();
					} catch (IOException e) {
						e.printStackTrace();
						server.stop();
					}
	    		}
	    	};
	    	
	    	new Thread(r).start();
	    	
	    	while (true) {
	    		client.openConnection(HOST, PORT);
	    		
	    		System.out.print("Enter message: ");
	    		String message = scan.nextLine();
	    		String response = client.sendMessage(message);
		    	System.out.println("Response received from server: " + response);
		    	
		    	client.closeConnection();
	    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		scan.close();
    		client.closeConnection();
    	}
    }
}
