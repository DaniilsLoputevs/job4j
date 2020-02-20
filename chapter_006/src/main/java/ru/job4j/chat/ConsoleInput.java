package ru.job4j.chat;

import java.util.Scanner;

/** Класс ввода фраз Пользователя, Боту, через консоль.
 * Внутри Scanner scanner, считывает строки из консоли.
 * Метод: next() - выдаёт строки Боту, на обработку.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 20.02.20.
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String next() {
        return scanner.nextLine();
    }
}