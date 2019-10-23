package ru.job4j.tracker;

/**
 * Interface для обобщёного обращения к Классас *ItemAction в папке actions
 * @author Daniils Loputevs
 * @version $Id$
 * @since 22.10.19
 **/

public interface UserAction {
    String name();

    boolean execute(Input input, Tracker tracker);
}