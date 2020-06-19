package ru.job4j.exam.players;

import ru.job4j.exam.Field;
import ru.job4j.exam.io.Input;
import ru.job4j.exam.io.Output;

/**
 * Player - AI.
 */
public class Ai extends BasePlayer {
    private Field gameField;

    public Ai(String name, char sign, Input input, Output output, Field gameField) {
        super(name, sign, input, output);
        this.gameField = gameField;
    }

    @Override
    public int[] makeMove() {
        return input.askTurnParam(genTurnParam());
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