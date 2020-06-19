package ru.job4j.exam.io;

/**
 * Class that contains methods that use in all {@code Input} implementation.
 * Plus base implementations of some methods.
 */
public abstract class BaseInput implements Input {

    /**
     * Convert Player turn params to normal format.
     * String -> int[].
     * * Convert *coordinates of cell* to *indexes of arrays*
     *
     * @param string - Player turn params for convert to normal format.
     * @return - turn param in normal format.
     */
    public int[] convertAnswer(String string) {
        var arr = string.split(" ");
        int[] result = new int[2];

        if (arr.length < 2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        // NumberFormatException
        result[0] = Integer.valueOf(arr[0]) - 1;
        result[1] = Integer.valueOf(arr[1]) - 1;
        return result;
    }

    @Override
    public int[] askTurnParam(int[] params) {
        return params;
    }
}