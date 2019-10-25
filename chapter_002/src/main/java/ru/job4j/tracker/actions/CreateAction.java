package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

/**
 * Создание заявки и добавление в tracker.items
 * @author Daniils Loputevs
 * @version $Id$
 * @since 24.10.19
 **/

public class CreateAction implements UserAction {
    @Override
    public String name() {
        return "=== Create a new Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
//        System.out.print("Enter name: "); // Если нужно вести текст вручную
//        String name = input.askStr("");
        tracker.add(new Item(input.askStr("")));
        System.out.println();
        return true;
    }
}