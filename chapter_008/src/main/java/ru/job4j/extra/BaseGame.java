package ru.job4j.extra;

import java.util.HashMap;
import java.util.List;

/**
 * Abstract base for implementation of {@code Game}.
 */
public abstract class BaseGame implements Game {
    protected HashMap<Player, Integer> playerPositionMap;

    @Override
    public void setPlayers(List<Player> playerList) {
        this.playerPositionMap = new HashMap<>(playerList.size());
        for (Player player : playerList) {
            this.playerPositionMap.put(player, 0);
        }
    }
}
