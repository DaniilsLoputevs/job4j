package ru.job4j.exam2.players;

import ru.job4j.exam2.Field;
import ru.job4j.exam2.GameTurn;

public interface Player {
    /**
     * Show to user the Game field.
     *
     * @param gameField - copy of gameField!
     */
    void acceptField(Field gameField);

    /**
     * Get Player's turn param.
     *
     * @return - turn param from player in Normal format.
     */
    GameTurn makeMve();

    /**
     * Accept message about: Wrong Turn Params OR Whose is a Winner.
     *
     * @param message - Message.
     */
    void acceptMessage(String message);
}
