package ru.job4j.tracker.item_actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

/**
 * Заглушка - Имитация действий для tracker.items
 * Нужна для тестов
 * @author Daniils Loputevs
 * @version $Id$
 * @since 25.10.19
 **/

public class StubAction implements UserAction {
    /**
     * переменная отвечает за вызов метода, если он был вызван, то call >> = true
     */
    private boolean call = false;


    @Override
    public String name() {
        return "Stub action";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        call = true;
        return false;
    }

    public boolean isCall() {
        return call;
    }
}