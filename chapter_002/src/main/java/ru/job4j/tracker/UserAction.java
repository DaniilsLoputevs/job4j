package ru.job4j.tracker;

/**
 * Interface для обобщёного обращения к Классас в папке actions.
 * @author Daniils Loputevs
 * @version $Id$
 * @since 17.11.19
 **/

public interface UserAction {
    /**
     * @return key - Номер с списке действий.
     */
    int key();

    /**
     * @return name - Название действия.
     */
    String name();

    /**
     * Выполняет основное действие объекта.
     * @return boolean - вызвано действие или нет + Выполнять программу после этого действия или закрыть?
     */
    boolean execute(Input input, Tracker tracker);
}