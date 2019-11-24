package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;

/**
 * Удаление заявки из tracker.items
 * @author Daniils Loputevs
 * @version $Id$
 * @since 25.10.19
 **/

public class Delete extends BaseAction {

    public Delete(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
//        System.out.print("Enter id: "); // Если нужно вести текст вручную
        String id =  input.askStr("");
        if (ValidateEnterData.checkId(id, tracker)) {
            tracker.delete(id);
            System.out.println();
        }
        return true;
    }
}
