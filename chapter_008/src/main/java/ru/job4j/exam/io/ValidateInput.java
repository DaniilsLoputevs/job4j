package ru.job4j.exam.io;

/**
 * Validate all date in {@code Input} implementation.
 * <p>
 * Catch Exceptions from BaseInput.convertAnswer(...) and "say" to player that's wrong.
 */
public class ValidateInput extends BaseInput {
    private Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public int[] askTurnParam(String enterParam, Output output) {
        boolean invalid = true;
        int[] value = new int[0];
        do {
            try {
                value = input.askTurnParam(enterParam, output);
                invalid = false;
            } catch (ArrayIndexOutOfBoundsException moe) {
                output.accept("Less that 2 arguments. Please enter date again:" + System.lineSeparator());
            } catch (NumberFormatException nfe) { // Integer.valueOf(arr[0]);
                output.accept("Please enter numbers. Please enter date again:" + System.lineSeparator());
            }
        } while (invalid);

        return value;
    }
}
