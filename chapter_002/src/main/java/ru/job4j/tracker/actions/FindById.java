package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.T

import java.util.function.Consumer;

/**
 * Вернуть заявку по id из tracker.items
 * @author Daniils Loputevs
 * @version $Id$
 * @since 23.12.19
 **/

public class FindById extends BaseAction {

    public FindById(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
//        System.out.print("Enter id: "); // Если нужно вести текст вручную
        String id =  input.askStr("");
        if (ValidateEnterData.checkId(id, tracker)) {
            Item local = tracker.findById(id);
            output.accept((String.format("%s %s", local.getId(), local.getName())));
        }
        return true;
    }
}
