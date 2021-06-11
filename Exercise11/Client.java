package model_p1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		int numberOfOperatingMachines = 0;

		for (int portNumber = 9991; portNumber <= 9994; portNumber++) {
			try {
				// connect to 4 remote machines
				Socket sock = new Socket("127.0.0.1", portNumber);

				// receive data from a remote machine
				DataInputStream dis = new DataInputStream(sock.getInputStream());
				String response = dis.readUTF();
				System.out.println("Machine " + portNumber + " is " + response);

				sock.close();

				numberOfOperatingMachines++;
			} catch (IOException ioe) {
				System.out.println("---");
			}
		}

		System.out.println("numberOfOperatingMachines: " + numberOfOperatingMachines);
	}
}
