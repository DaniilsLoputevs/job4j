package ru.job4j.exam2.players;

import ru.job4j.exam2.Field;
import ru.job4j.exam2.GameTurn;
import ru.job4j.exam2.io.Output;

public class StubPlayer implements Player {
    protected String name;
    protected char sign;
    protected Output output;

    public StubPlayer(String name, char sign, Output output) {
        this.name = name;
        this.sign = sign;
        this.output = output;
    }

    @Override
    public void acceptField(Field gameField) {

    }

    @Override
    public GameTurn makeMve() {
        return new GameTurn(this.name, this.sign, new int[] {0, 0});
    }

    @Override
    public void acceptMessage(String message) {
        output.accept(message);
    }
}
