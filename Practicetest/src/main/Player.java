package main;

import java.io.Serializable;

public class Player implements Serializable {
    private String playerRole;
    private int playerRow, playerColumn;

    Player(String playerRole, int playerRow, int playerColumn){
        playerRole = this.playerRole;
        playerRow = this.playerRow;
        playerColumn = this.playerColumn;
    }

    public String getPlayerRole() {
        return playerRole;
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public int getPlayerColumn() {
        return playerColumn;
    }

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    public void setPlayerRow(int playerRow) {
        this.playerRow = playerRow;
    }

    public void setPlayerColumn(int playerColumn) {
        this.playerColumn = playerColumn;
    }
}