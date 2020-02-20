package ru.job4j.chat;

import java.util.LinkedList;

/** Класс вывода ответа Бота, куда-нибудь. являеться буффером/очередью.
 * Метод: add() - добавляет ответ Бота в буффер.
 * Метод: poll() - выдаёт + удаляет ответ Бота из буффер.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 20.02.20.
 */
public class Output {
    private LinkedList<String> container = new LinkedList<>();

    public void add(String botPhrase) {
        container.add(botPhrase);
    }

    public String poll() {
        var result = container.poll();
        return result.substring(0, result.length() - 1);
    }
}