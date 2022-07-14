package examples.echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private BufferedReader in;
	private PrintStream out;
	
	public EchoServer(int port) throws IOException {
		super();
		this.serverSocket = new ServerSocket(port);
		this.clientSocket = null;
		this.in = null;
		this.out = null;
	}
	
	public void start() throws IOException {
		while (true) {
			try {
				clientSocket = this.serverSocket.accept();
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out = new PrintStream(clientSocket.getOutputStream());
				
				String message = in.readLine();
				System.out.println("Message received from client: " + message);
				out.println(message);
				
				out.close();
				in.close();
				clientSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		try {
			this.out.close();
			this.in.close();
			this.clientSocket.close();
			this.serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
