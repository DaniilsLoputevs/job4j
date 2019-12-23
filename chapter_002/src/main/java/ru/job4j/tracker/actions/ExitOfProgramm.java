package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;

import java.util.function.Consumer;

/**
 * Выход из программы
 * @author Daniils Loputevs
 * @version $Id$
 * @since 23.12.19
 **/

public class ExitOfProgramm extends BaseAction {

    public ExitOfProgramm(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        return false;
    }
}
