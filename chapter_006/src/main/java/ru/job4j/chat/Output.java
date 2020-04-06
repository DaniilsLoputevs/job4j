package ru.job4j.chat;

import java.util.LinkedList;

/** Класс вывода ответа Бота, куда-нибудь. является буфером/очередью.
 * Метод: add() - добавляет ответ Бота в буфер.
 * Метод: poll() - выдаёт + удаляет ответ Бота из буфер.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 20.02.20.
 * Last upd:  24.02.20.
 * Last JavaDoc upd:  05.03.20.
 */
public class Output {
    private LinkedList<String> container = new LinkedList<>();

    public void add(String botPhrase) {
        container.add(botPhrase);
    }

    public String poll() {
        return container.poll();
    }
}