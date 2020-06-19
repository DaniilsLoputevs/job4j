package ru.job4j.exam2.io;

import ru.job4j.exam2.Field;
import ru.job4j.exam2.GameTurn;
import ru.job4j.exam2.players.Player;

import java.util.List;

public class ConsoleInput implements Input {
    private final List<Player> players;
    private PlayerIterator iterator;
    private Player currentPlayer;


    public ConsoleInput(List<Player> players) {
        this.players = players;
        this.iterator = new PlayerIterator(players);
    }

    @Override
    public void acceptField(Field copyOfGameField) {
        this.currentPlayer.acceptField(copyOfGameField);
    }

    @Override
    public GameTurn nextPlayerTurnParam() {
        return this.currentPlayer.makeMve();
    }

    @Override
    public GameTurn askPlayerEnterParamAgain(String waringMessage) {
        this.currentPlayer.acceptMessage(waringMessage);
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

}