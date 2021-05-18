package main;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface serverService extends Remote {
    public void printBoard() throws  RemoteException;
    public boolean checkWinner(String playerRole) throws RemoteException;
    public boolean checkDraw();
    public String getWinner();
    public String getCurrentPlayer();
    public void setPlayerMove(int playerRow, int playerColumn, String playerRole) throws RemoteException, IOException;
    public int playerAuth(String playerUsername, String playerPassword) throws RemoteException, IOException;
}