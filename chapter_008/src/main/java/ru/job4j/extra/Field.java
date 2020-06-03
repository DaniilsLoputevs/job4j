package ru.job4j.extra;

import ru.job4j.extra.fieldaction.FieldAction;

/**
 * Interface for game field.
 */
public interface Field {
    /**
     * Get action on game field
     *
     * @param position - num of "game cell" - where could be action.
     * @return - null - if "game cell" doesn't contains action.
     */
    FieldAction getActionByPosition(int position);

    /**
     * @return - get size of game field.
     */
    int getCountOfField();
}
