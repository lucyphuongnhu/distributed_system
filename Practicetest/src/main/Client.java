package main;

import java.rmi.RemoteException;
import java.util.*;

public class Client {
    static String board[][];
    static String playerRole;

    static void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + board[i][0] + " | " + board[i][1] + " | " + board[i][2] + " |");
            System.out.println("-------------");
        }
    }

    public static String checkWinner() throws RemoteException{
        // Check for a winning line - diagonals first
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2])
                || (board[0][2] == board[1][1] && board[0][2] == board[2][0])) {
            if (board[1][1] == playerRole)
                return playerRole;
        }
        else {
            //Check rows and columns for a winning line
            for (int i = 0; i <= 2; i++) {
                if ((board[i][0] == board[i][1] && board[i][0] == board[i][2])) {
                    if (board[i][0] == playerRole)
                        return playerRole;
                }

                else if ((board[0][i] == board[1][i] && board[0][i] == board[2][i])) {
                    if (board[0][i] == playerRole)
                        return playerRole;
                }
            }
        }
        return "null";
    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            board = new String[3][3];
            playerRole = "X";
            String winner = "null";

            //Create a board to play Tic Tac Toe
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++){
                    board[i][j] = " ";
                }
            }
            System.out.println("Welcome to 3x3 Tic Tac Toe.");
            printBoard();

            // Play Tic Tac Toe
            while (winner == "null") {
                //Enter Row & Column
                System.out.println("Player " + playerRole + " enters your move (row[1-3] column[1-3]):");

                int playerRow, playerColumn;

                try {
                    playerRow = in.nextInt();
                    playerColumn = in.nextInt();

                    if (!((playerRow > 0 && playerRow <=3) && (playerColumn > 0 && playerColumn <=3))) {
                        System.out.println("Invalid input; re-enter slot number:");
                        continue;
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }

                //Check chosen Row & Column
                if (!(board[playerRow - 1][playerColumn - 1].equals("X")
                        || board[playerRow - 1][playerColumn - 1].equals("O") )) {
                    board[playerRow - 1][playerColumn - 1] = playerRole;
                    printBoard(); //print board after choosing

                    //Check winner
                    winner = checkWinner();
                    if (winner != "null"){
                        System.out.println("Congratulations player " + winner);
                        break;
                    }

                    if (playerRole.equals("X")) {
                        playerRole = "O";
                    }
                    else {
                        playerRole = "X";
                    }

                    printBoard();

                    //Check winner
                    winner = checkWinner();
                    if (winner != "null"){
                        System.out.println("Congratulations player " + winner);
                        break;
                    }
                }
                else {
                    System.out.println("Slot already taken; re-enter slot number:");
                }

            }
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }
}