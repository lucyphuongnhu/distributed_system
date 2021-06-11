//import java.net.InetAddress;

import org.apache.xmlrpc.*;

public class XMLRPCServer {
	public int sum(int x, int y) {
		return (x + y);
	}

	public int subtract(int x, int y) {
		return (x - y);
	}

	public int multiply(int x, int y) {
		return (x * y);
	}

	public float divide(int x, int y) {
		return (x / y);
	}

	public static void main(String[] args) {
		try {
			// setup server
			WebServer server = new WebServer(90);
			
			// add services
			server.addHandler("JavaServer", new XMLRPCServer());
			
			// start server
			server.start();
			System.out.println("Started successfully.");
		} catch (Exception exception) {
			System.err.println("JavaServer: " + exception);
		}
	}
}
