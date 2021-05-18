package main;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server implements serverService {
    private String[][] board = { { "   ", "   ", "   "}, { "   ", "   ", "   "}, { "   ", "   ", "   "} };
    private String currentPlayer = "X";
    private String winnerPlayer = "null";
    private int moveCount = 0;

    //Function to print board
    public void printBoard() throws  RemoteException{
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + board[i][0] + " | " + board[i][1] + " | " + board[i][2] + " |");
            System.out.println("-------------");
        }
    }

    //Function for Player to move
    public void setPlayerMove(int playerRow, int playerColumn, String playerRole) throws RemoteException, IOException{
        //Limit Row and Column
        if ((playerRow > 0 && playerRow <=3) && (playerColumn > 0 && playerColumn <=3)) {
            playerRow -= 1;
            playerColumn -= 1;
        }
        else{
            throw new IOException("Invalid input; re-enter slot number");
        }

        //Check the Players
        if (!(playerRole.equals("X") || playerRole.equals("O"))) {
            throw new IOException("No specific player " + playerRole);
        }

        //Check Player's turn
        if (!this.currentPlayer.equals(playerRole)) {
            throw new IOException("Wrong turn !");
        }

        if (!(board[playerRow - 1][playerColumn - 1].equals("X")
                || board[playerRow - 1][playerColumn - 1].equals("O") )){
            board[playerRow - 1][playerColumn - 1] = playerRole;
        }
        else{
            throw new IOException("Already exists move!");
        }

        String input = " " + playerRole + " ";
        // Otherwise, makes change to the gameBoard
        board[playerRow][playerColumn] = input;

        if (checkWinner(this.currentPlayer)) {
            this.winnerPlayer = this.currentPlayer;
        };
        // Switch player
        this.currentPlayer = (currentPlayer == "X") ? "O" : "X";

        this.moveCount++;
    }

    //Function to check Winner
    public boolean checkWinner(String playerRole) throws RemoteException{
        // Check for a winning line - diagonals first
        if ((board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]))
                || (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]))) {
            if (board[1][1].equals(playerRole))
                return true;
        }
        else {
            //Check rows and columns for a winning line
            for (int i = 0; i <= 2; i++) {
                if ((board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]))) {
                    if (board[i][0].equals(playerRole))
                        return true;
                }
                if ((board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]))) {
                    if (board[0][i].equals(playerRole))
                        return true;
                }
            }
        }
        return false;
    }

    //Function to check Draw
    public boolean checkDraw() {
        if (this.winnerPlayer == null && this.moveCount == 9) {
            return true;
        } else {
            return false;
        }
    }

    public String getWinner() {
        return this.winnerPlayer;
    }

    public String getCurrentPlayer() {
        return this.currentPlayer;
    }

    public int playerAuth(String playerUsername, String playerPassword) throws RemoteException, IOException {
        int gameId = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/distributedsystem",
                    "newuser", "password");
            PreparedStatement statement = connection.prepareStatement("SELECT playerAuthID FROM playerAuth WHERE playerUsername = ? AND playerPassword = ?");
            statement.setString(1, playerUsername);
            statement.setString(2, playerPassword);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                gameId = resultSet.getInt(1);
                return gameId;
            } else {
                return 0;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
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