package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

/**
 * Вернуть все заявки из tracker.items
 * @author Daniils Loputevs
 * @version $Id$
 * @since 25.10.19
 **/

public class FindAll extends BaseAction {

    public FindAll(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        for (Item item : tracker.findAll()) {
            System.out.println(String.format("%s %s", item.getId(), item.getName()));
            System.out.println();
        }
        return true;
    }
}
