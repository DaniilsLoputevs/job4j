package ru.job4j.exam.io;


public interface Input {

    /**
     * Wait Enter date from real Player.
     *
     * @param enterParam - String that need to show to player.
     * @param output     - User = System.out.print();
     * @return - params that change the game field.
     */
    int[] askTurnParam(String enterParam, Output output);

    /**
     * Ask AI enter turn param.
     *
     * @param params - generated params.
     * @return - params that change the game field.
     */
    int[] askTurnParam(int[] params);
}
