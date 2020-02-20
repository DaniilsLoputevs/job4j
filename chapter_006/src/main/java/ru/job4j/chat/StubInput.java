package ru.job4j.chat;

/** Класс для JUnit tests
 * через конструктор в формате List, задаются фразы пользователя.
 * Метод: next() - по очерёдно выдаёт фразы Боту, иметирую по очерёдный диалог с ботом.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 20.02.20.
 */
public class StubInput implements Input {
    private String[] container;
    private int index = 0;

    public StubInput(String[] container) {
        this.container = container;
    }

    @Override
    public String next() {
        String result = null;
        if (index < container.length) {
            result = container[index++];
        } else {
            System.out.println("Ошибка: В контейнере нет более строк.");
        }
        return result;
    }
}
