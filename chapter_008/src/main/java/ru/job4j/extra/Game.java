package ru.job4j.extra;

import java.util.List;

/**
 * Interface for game.
 */
public interface Game {
    /**
     * Main game method - start game.
     *
     * @param playerList - List with all players.
     * @param field      - Game field.
     */
    void start(List<Player> playerList, Field field);

    /* ------- methods for use in start(...) --------- */

    /**
     * Set players for private var in class.
     *
     * @param playerList - players
     */
    void setPlayers(List<Player> playerList);

    /**
     * get player's position.
     *
     * @param player - player.
     * @return - position
     */
    int getPlayerPosition(Player player);

    /**
     * Upd player position.
     *
     * @param player      - player.
     * @param newPosition - new player's position..
     */
    void setPlayerPosition(Player player, int newPosition);
}
