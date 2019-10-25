package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

/**
 * Вернуть заявку по name из tracker.items
 * @author Daniils Loputevs
 * @version $Id$
 * @since 24.10.19
 **/

public class FindByNameItem implements UserAction {
    @Override
    public String name() {
        return "=== Find Item by Name ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
//        System.out.print("Enter id: "); // Если нужно вести текст вручную
        String name =  input.askStr("");
        for (Item item : tracker.findByName(name)) {
            System.out.println(String.format("%s %s", item.getId(), item.getName()));
        }
        return true;
    }
}
