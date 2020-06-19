package ru.job4j.exam2.io;

import ru.job4j.exam2.Field;
import ru.job4j.exam2.GameTurn;

public interface Input {

    void acceptField(Field gameField);

    GameTurn nextPlayerTurnParam();

    GameTurn askPlayerEnterParamAgain(String waringMessage);

    void showMessageToPlayer(Object message);

    void showMessageForEachPlayer(String message);

    boolean hasNextPlayer();

    void setNextPlayer();

    void newPlayerRound();

}
