package examples.echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
	private Socket socket;
	private BufferedReader in;
	private PrintStream out;

	public void openConnection(String host, int port) throws UnknownHostException, IOException {
		this.socket = new Socket(host, port);
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.out = new PrintStream(this.socket.getOutputStream());
	}
	
	public String sendMessage(String message) throws IOException {
		String response = "";
		out.println(message);
		response = in.readLine();
		return response;
	}
	
	public void closeConnection() {
		try {
			this.out.close();
			this.in.close();
			this.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
