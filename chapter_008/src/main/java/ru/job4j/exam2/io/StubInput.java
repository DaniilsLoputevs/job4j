package ru.job4j.exam2.io;

import ru.job4j.exam2.Field;
import ru.job4j.exam2.GameTurn;
import ru.job4j.exam2.players.Player;

import java.util.List;

/**
 * Stub Input for tests.
 */
public class StubInput implements Input {
    /* - input variables - */
    private final List<Player> players;
    private PlayerIterator iterator;
    private Player currentPlayer;

    /* - stub input variables - */
    private int[][] array;
    private int position = 0;

    public StubInput(List<Player> players) {
        this.players = players;
        this.iterator = new PlayerIterator(players);
    }


    @Override
    public void acceptField(Field copyOfGameField) {
//        /* - debug - */
//        ArrayPrintHelper.print(copyOfGameField.getField());
//        /* - debug - */
    }

    @Override
    public GameTurn nextPlayerTurnParam() {
        var temp = this.currentPlayer.makeMve();
        var params = array[position++];
        params[0] += -1;
        params[1] += -1;
        temp.setTurnParams(params);
        return temp;
    }

    @Override
    public GameTurn askPlayerEnterParamAgain(String waringMessage) {
//        /* - debug - */
//        System.out.println(waringMessage);
//        /* - debug - */
        return this.currentPlayer.makeMve();
    }

    @Override
    public void showMessageToPlayer(Object message) {
        this.currentPlayer.acceptMessage(message.toString());
    }

    @Override
    public void showMessageForEachPlayer(String message) {
        for (var player : players) {
            player.acceptMessage(message);
        }
//        /* - debug - */
//        System.out.println(message);
//        /* - debug - */
    }

    /* ------ iterator's methods ------ */

    @Override
    public boolean hasNextPlayer() {
        return this.iterator.hasNext();
    }

    @Override
    public void setNextPlayer() {
        this.currentPlayer = this.iterator.next();
    }

    @Override
    public void newPlayerRound() {
        this.iterator = new PlayerIterator(players);
    }

    /* ------- Setter ------- */

    public void setArray(int[][] array) {
        this.array = array;
    }
}