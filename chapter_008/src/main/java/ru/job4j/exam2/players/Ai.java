package ru.job4j.exam2.players;

import ru.job4j.exam2.Field;
import ru.job4j.exam2.GameTurn;
import ru.job4j.exam2.io.Output;

public class Ai implements Player {
    protected String name;
    protected char sign;
    protected Output output;
    private Field gameField;

    public Ai(String name, char sign, Output output) {
        this.name = name;
        this.sign = sign;
        this.output = output;
    }
    @Override
    public void acceptField(Field copyOfGameField) {
        this.gameField = copyOfGameField;
    }


    @Override
    public GameTurn makeMve() {
        return new GameTurn(this.name, this.sign, genTurnParam());
//        return null;
    }

    @Override
    public void acceptMessage(String message) {
        output.accept(message);
    }

    /**
     * Create AI turn params.
     * Use Game Field to find empty space.
     *
     * @return - AI turn params.
     */
    private int[] genTurnParam() {
        var result = new int[2];
        int size = gameField.getField().length;
        bigLoop:
        for (int row = size - 1; row >= 0; row--) {
            for (int column = size - 1; column >= 0; column--) {
                if (gameField.isCellFree(row, column)) {
                    result[0] = row;
                    result[1] = column;
                    break bigLoop;
                }
            }
        }
        return result;
    }

}
