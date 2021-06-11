package model_p1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Server1 {
	public static void main(String[] args) {
		try {
			ServerSocket sock = new ServerSocket(9991);

			while (true) {
				System.out.println("Listening!!!");
				Socket client = sock.accept();
				
				// process
				DataOutputStream dos = 
						new DataOutputStream(client.getOutputStream());
				dos.writeUTF("OK");

				client.close();
			}
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}
}
