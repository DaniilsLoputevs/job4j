package ru.job4j.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Класс содержащий универсальные и вспомогательные методы для работы со String.
 */
public class StringHelper {

    /** добавляет в конец каждой строки "Строчный разделитель".
     *** Метод создан на основе List.of(...).
     * @param input строки.
     * @return массив из этих строк с разделителями.
     */
    public static String[] separateLines(String... input) {
        String[] tmp = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            tmp[i] = Objects.requireNonNull(input[i]) + System.lineSeparator();
        }
        return tmp;
    }

    /** преобразует строки в List<String>
     * через ',' добавляем строки. Аналог List.of()
     * @param lines - строки. (через ',' добавляем строки)
     * @return List<String>
     */
    public static List<String> linesToList(String... lines) {
        return Arrays.stream(lines).collect(Collectors.toList());
    }


}