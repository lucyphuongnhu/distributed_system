package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Server implements serverService {
    Connection databaseConnect;

    public Server() throws Exception {
        this.databaseConnect = connectToDatabase("newuser", "password");
        if (databaseConnect != null) {
            System.out.println("Connected to the database");
        } else {
            throw new Exception("Cannot connect to database");
        }
    }

    public static Connection connectToDatabase(String user, String password) {
        String databaseUrl = "jdbc:mysql://localhost:3306/distributed?user=" + user + "&password=" + password;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseUrl);
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        return conn;
    }

    public List<Player> getPlayer() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM player";
        Statement stmt;

        try {
            stmt = databaseConnect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                String playerRole = rs.getString("playerRole");
                int playerRow = rs.getInt("playerRow");
                int playerColumn = rs.getInt("playerColumn");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }

    public void insertPlayer(String playerRole, int playerRow, int playerColumn) throws RemoteException {
        try {
            PreparedStatement pstmt = databaseConnect.prepareStatement("INSERT INTO player VALUES(?,?,?)");
            pstmt.setString(1, playerRole);
            pstmt.setInt(2, playerRow);
            pstmt.setInt(3, playerColumn);
            pstmt.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // create instant of implemnetation
        Server obj = new Server();

        serverService stub = (serverService) UnicastRemoteObject.exportObject(obj, 1099);

        // Register stub to RMI server
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("serverService", stub);

        System.out.println("Server ready");
    }
}