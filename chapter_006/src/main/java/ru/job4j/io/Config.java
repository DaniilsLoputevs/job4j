package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /** Загружает настройки из файла. (путь указываеться в Конструкторе)
     * С начала, проверка что с файлов всё впорядке.
     * Далее считываем всё в буффер, фильтруем от комментов и т.д.
     * Закидываем в карту:
     * Тип настроек - как ключ.
     * Значение настроек - как значение.
     */
    public void load() {
        List<String> fileLines = new LinkedList<>();
        try (BufferedReader load = new BufferedReader(new FileReader(path))) {
            fileLines = load.lines().collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.out.println("IOException - something wrong!");
            e.printStackTrace();
        }

        values.putAll(fileLines.stream()
                .filter(line -> !line.startsWith("//"))
                .filter(line -> !line.startsWith("#"))
                .filter(line -> !line.startsWith(" "))
                .filter(line -> line.contains("="))
                .collect(Collectors.toMap(
                        line -> line.substring(0, line.indexOf("=")),
                        line -> line.substring(line.indexOf("=") + 1))));
    }

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