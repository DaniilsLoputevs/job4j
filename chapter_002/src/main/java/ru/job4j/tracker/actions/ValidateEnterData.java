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
        if (tracker.containsId(id)) {
            result = true;
        } else {
            System.out.println("Такого id не существует.");
        }
        return result;
    }
    static boolean checkName(String name, Tracker tracker) {
        boolean result = false;
        if (tracker.containsName(name)) {
            result = true;
        } else {
            System.out.println("Такого Имя не существует.");
        }
        return result;
    }
}
