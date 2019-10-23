package ru.job4j.tracker;

/**
 * Замена Scanner scanner т.к. нужно как меньше внешних зависимостей
 * Все методы выполняют одну функцию.
 * @author Daniils Loputevs
 * @version $Id$
 * @since 22.10.19
 **/

public interface Input {

    String askStr(String question);

    int askInt(String question);

    int askInt(String question, int max);
}