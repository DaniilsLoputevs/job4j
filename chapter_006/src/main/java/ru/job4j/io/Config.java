package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/** Читаем файл конфигурации.
 * Задача: Чситать содержимое файла configuration.properties и сохранить Настройки во внутрению карту.
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /** Загружает настройки из файла по пути path(инит. через конструктор).
     * через stream фильтруем от комментов и т.д.
     * Собираем карту:
     * Тип настроек      - Ключ.
     * Значение настроек - Значение.
     */
    public void load() {
        List<String> fileLines = Helper.readFileToList(path);

        values.putAll(fileLines.stream()
                .filter(line -> !line.startsWith("//"))
                .filter(line -> !line.startsWith("#"))
                .filter(line -> !line.startsWith(" "))
                .filter(line -> line.contains("="))
                .collect(Collectors.toMap(
                        line -> line.substring(0, line.indexOf("=")),
                        line -> line.substring(line.indexOf("=") + 1))));
    }

    /** Получить значение по ключу.
     * @param key ключ
     * @return value/null
     */
    public String value(String key) {
        if (values.isEmpty()) {
            throw new UnsupportedOperationException("Don't impl this method yet! || It's empty map");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}