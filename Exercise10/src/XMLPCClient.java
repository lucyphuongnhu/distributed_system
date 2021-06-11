import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import org.apache.xmlrpc.*;

public class XMLPCClient {
	public static void main(String[] args) {
		try {
			// XmlRpcClient
			XmlRpcClient client =  new XmlRpcClient("http://localhost:90");

			while (true) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("First number");
				int num1 = Integer.parseInt(br.readLine());
				System.out.println("Second number");
				int num2 = Integer.parseInt(br.readLine());
				
				// params
				Vector params = new Vector();
				params.addElement(num1);
				params.addElement(num2);

				// call a remote function
				Object result =  client.execute("JavaServer.sum", params);

				// process result
				int sum = ((Integer) result).intValue();
				System.out.println("The sum is: " + sum);
			}
			
			
		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
	}
}
