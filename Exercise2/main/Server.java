package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	public static int insertStudent(int studentID, String studentName, String studentYear) {
		// for insert a new candidate
		ResultSet rs = null;
		int sId = 0;

		String sql = "INSERT INTO studentdb(studentID, studentName, studentYear) VALUES(?,?,?)";

		try (Connection conn = MyDBUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// set parameters for statement
			pstmt.setInt(1, studentID);
			pstmt.setString(2, studentName);
			pstmt.setString(3, studentYear);
			pstmt.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(rs != null)  rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		return sId;
	}

	public static void main(String[] args) {
		int serverPort = 1999;
		ServerSocket serverSocket = null;
		ObjectOutputStream toClient = null;
		ObjectInputStream fromClient = null;
		
		try {
			serverSocket = new ServerSocket(serverPort);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Just connected to " + socket.getRemoteSocketAddress());
				
				toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				fromClient = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				Student msgRequest = (Student) fromClient.readObject();
				int studentID = msgRequest.getStudentID();
				String studentName = msgRequest.getStudentName();
				String studentYear = msgRequest.getStudentYear();
				insertStudent(studentID,studentName,studentYear);

				toClient.writeObject(new Student(studentID,studentName,studentYear));
				toClient.flush();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
