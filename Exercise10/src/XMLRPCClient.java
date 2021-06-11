import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import org.apache.xmlrpc.*;

public class XMLRPCClient {
	public static void main(String[] args) {
		try {
			// XmlRpcClient
			XmlRpcClient client =  new XmlRpcClient("http://localhost:99");

			while (true) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				//Choose an option to calculate
				System.out.println("Please choose an option: ");
				System.out.println("1 - Addition");
				System.out.println("2 - Subtraction");
				System.out.println("3 - Multiplicity");
				System.out.println("4 - Division");
				System.out.print("Your option is ");
				int option = Integer.parseInt(br.readLine());

				//Input two numbers
				System.out.println("First number");
				int num1 = Integer.parseInt(br.readLine());
				System.out.println("Second number");
				int num2 = Integer.parseInt(br.readLine());
				
				// params
				Vector params = new Vector();
				params.addElement(num1);
				params.addElement(num2);

				// call a remote function
				Object result =  null;
				switch (option){
					case 1:
						result = client.execute("JavaServer.sum", params);
						int sum = ((Integer) result).intValue();
						System.out.println("The sum is: " + sum);
						break;
					case 2: 
						result = client.execute("JavaServer.subtract", params);
						int subtract = ((Integer) result).intValue();
						System.out.println("The subtract is: " + subtract);
						break;
					case 3:
						result = client.execute("JavaServer.multiply", params);
						int multiply = ((Integer) result).intValue();
						System.out.println("The multiply is: " + multiply);
						break;
					case 4: 
						result = client.execute("JavaServer.divide", params);
						float divide = ((Float) result).floatValue();
						System.out.println("The divide is: " + divide);
						break;
				}
			}
		} 
		catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
	}
}
