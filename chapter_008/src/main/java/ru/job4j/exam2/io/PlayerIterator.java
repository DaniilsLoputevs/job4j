package ru.job4j.exam2.io;

import ru.job4j.exam2.players.Player;

import java.util.Iterator;
import java.util.List;

public class PlayerIterator implements Iterator<Player> {
    private List<Player> players;
    private int position = 0;

    public PlayerIterator(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean hasNext() {
        return position < players.size();
    }

    @Override
    public Player next() {
        Player result = null;
        if (hasNext()) {
            result = players.get(position++);
        }
        return result;
    }
}