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

public class socketServer(){
    public static int playerAuth(String playerUsername, String playerPassword){
        ResultSet rs = null;
        int gameID = 0;
        try{
            String sql = "SELECT playerAuthID FROM playerAuth WHERE playerUsername = ? AND playerPassword = ?";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/distributedsystem", "newuser", "password");
            PreparedStatement pstmt = conn.preparedStatment(sql);
            pstmt.setInt(1, playerUsername);
            pstmt.setString(2, playerPassword);
            pstmt.execute();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        finally {
            try {
                if(rs != null)
                    rs.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return gameID;
    }
}

public static void main(String[] args){
    int serverPort = 1999;
    ServerSocket serverSocket = null;
    DataOutputStream toClient = null;
    DataInputStream fromClient = null;

    try{
        serverSocket = new ServerSocket(serverPort);
        Socket socket = serverSocket.accept();

        toClient = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        fromClient = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        String username = fromClient.readUTF();
        String password = fromClient.readUTF();

        

    }
}