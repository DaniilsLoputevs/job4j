package ru.job4j.extra;

/**
 * Interface for game dice.
 */
public interface GameTurn {
    /**
     * Add info about game like a string.
     *
     * @param string - info.
     */
    void acceptInfo(String string);

    /**
     * @return - return info.
     */
    String getInfo();
}
