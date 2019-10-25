package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

/**
 * Заменить заявку по id на новую заявку в tracker.items
 * @author Daniils Loputevs
 * @version $Id$
 * @since 24.10.19
 **/

public class ReplaceItem implements UserAction {
    @Override
    public String name() {
        return "=== Replace Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
//        System.out.print("Enter id: "); // Если нужно вести текст вручную
        String id = input.askStr("");
//        System.out.print("Enter name: ");  // Если нужно вести текст вручную
        String name =  input.askStr("");
        Item item = new Item(name);
        tracker.replace(id, item);
        System.out.println();
        return true;
    }
}
