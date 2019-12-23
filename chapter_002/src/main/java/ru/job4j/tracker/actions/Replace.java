package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.function.Consumer;

/**
 * Заменить заявку по id на новую заявку в tracker.items
 * @author Daniils Loputevs
 * @version $Id$
 * @since 23.12.19
 **/

public class Replace extends BaseAction {

    public Replace(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
//        output.accept(("Enter id in tracker for replace: "));  // Если нужно вести текст вручную
        String id = input.askStr("");
        if (ValidateEnterData.checkId(id, tracker)) {
//          output.accept(("Enter name for new item: "));    // Если нужно вести текст вручную
            String name = input.askStr("");
            if (ValidateEnterData.checkName(id, tracker)) {
                Item item = new Item(name);
                tracker.replace(id, item);
                System.out.println();
            }
        }
        return true;
    }
}
