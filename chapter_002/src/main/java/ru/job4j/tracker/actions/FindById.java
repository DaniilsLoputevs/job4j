package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

/**
 * Вернуть заявку по id из tracker.items
 * @author Daniils Loputevs
 * @version $Id$
 * @since 25.10.19
 **/

public class FindById extends BaseAction {

    public FindById(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
//        System.out.print("Enter id: "); // Если нужно вести текст вручную
        String id =  input.askStr("");
        if (ValidateEnterData.checkId(id, tracker)) {
            Item local = tracker.findById(id);
            System.out.print(String.format("%s %s", local.getId(), local.getName()));
            System.out.println();
        }
        return true;
    }
}
