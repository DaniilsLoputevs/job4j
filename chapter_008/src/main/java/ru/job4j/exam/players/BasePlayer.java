package ru.job4j.exam.players;

import ru.job4j.exam.io.Input;
import ru.job4j.exam.io.Output;

/**
 * Abstract base for Player.
 * Contains Interface methods that used by Player.
 */
public abstract class BasePlayer {
    protected String name;
    protected char sign;
    protected Input input;
    protected Output output;

    public BasePlayer(String name, char sign, Input input, Output output) {
        this.name = name;
        this.sign = sign;
        this.input = input;
        this.output = output;
    }

    /**
     * Wait enter dates from Player.
     * -> Wait real human will make turn.
     * -> Wait AI will make turn.
     *
     * @return - converted Params from user.
     */
    abstract public int[] makeMove();


    public String getName() {
        return name;
    }

    public char getSign() {
        return sign;
    }

    public Input getInput() {
        return input;
    }

    public Output getOutput() {
        return output;
    }
}
