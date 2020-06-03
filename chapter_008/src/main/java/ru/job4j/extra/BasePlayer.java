package ru.job4j.extra;

/**
 * abstract base for implementation of {@code Player}.
 */
public abstract class BasePlayer implements Player {
    protected String name;

    public BasePlayer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
