package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

/**
 * Вернуть заявку по id из tracker.items
 * @author Daniils Loputevs
 * @version $Id$
 * @since 24.10.19
 **/

public class FindByIdItem implements UserAction {
    @Override
    public String name() {
        return "=== Find Item by Id ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
//        System.out.print("Enter id: "); // Если нужно вести текст вручную
        String id =  input.askStr("");
        tracker.findById(id);
        System.out.println();
        return true;
    }
}
