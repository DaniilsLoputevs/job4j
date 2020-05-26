package ru.job4j.exam.players;

import ru.job4j.exam.io.Input;
import ru.job4j.exam.io.Output;

/**
 * Player - Real User.
 */
public class User extends BasePlayer {
    public User(String name, char sign, Input input, Output output) {
        super(name, sign, input, output);
    }

    @Override
    public int[] makeMove() {
        return input.askTurnParam("Enter turn param: ", output);
    }
}
