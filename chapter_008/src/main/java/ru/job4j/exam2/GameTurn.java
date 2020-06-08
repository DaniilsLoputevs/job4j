package ru.job4j.exam2;

public class GameTurn {
    String playerName;
    char playerSign;
    int[] turnParams;
//    Output output;

    public GameTurn(String playerName, char playerSign, int[] turnParams
//            , Output output
    ) {
        this.playerName = playerName;
        this.playerSign = playerSign;
        this.turnParams = turnParams;
//        this.output = output;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerSign(char playerSign) {
        this.playerSign = playerSign;
    }

    public void setTurnParams(int[] turnParams) {
        this.turnParams = turnParams;
    }
}
