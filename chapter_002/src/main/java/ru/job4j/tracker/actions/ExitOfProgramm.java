package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

/**
 * Выход из программы
 * @author Daniils Loputevs
 * @version $Id$
 * @since 24.10.19
 **/

public class ExitOfProgramm implements UserAction {
    @Override
    public String name() {
        return "=== Exit ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        return false;
    }
}
