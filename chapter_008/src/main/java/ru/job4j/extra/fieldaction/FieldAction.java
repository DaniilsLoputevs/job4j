package ru.job4j.extra.fieldaction;

/**
 * Interface for Field Action.
 */
public interface FieldAction {
    String info();

    /**
     * Do action.
     *
     * @param playerPosition - Current Player position.
     * @return - Player's position after action.
     */
    int execute(int playerPosition);
}
