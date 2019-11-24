package ru.job4j.tracker.actions;

import ru.job4j.tracker.Tracker;

/**
 * Проверка существуют ли, такие данные в Tracker tracker.
 * @author Daniils Loputevs
 * @version $Id$
 * @since 24.11.19
 **/

class ValidateEnterData {
    static boolean checkId(String id, Tracker tracker) {
        boolean result = false;
        if (tracker.contains(id)) {
            result = true;
        } else {
            System.out.println("Такого id не существует.");
        }
        return result;
    }
}
