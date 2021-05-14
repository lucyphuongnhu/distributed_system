package main;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface serverService extends Remote {
    public List<Player> getPlayer() throws RemoteException;
    public void insertPlayer(String playerRole, int playerRow, int playerColumn) throws RemoteException;
}