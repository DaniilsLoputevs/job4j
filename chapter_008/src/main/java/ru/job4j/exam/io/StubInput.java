package ru.job4j.exam.io;

/**
 * Stub Input for tests.
 */
public class StubInput extends BaseInput {
    private String[] array;
    private int position = 0;

    public StubInput(String[] array) {
        this.array = array;
    }

    @Override
    public int[] askTurnParam(String enterParam, Output output) {
        return convertAnswer(array[position++]);
    }
}
