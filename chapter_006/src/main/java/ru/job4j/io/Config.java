package ru.job4j.io;

import daniils.IOHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/** Читаем файл конфигурации.
 * Задача: Считать содержимое файла configuration.properties.
 * и сохранить "Настройки" во внутреннюю map<String, String>.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 16.02.20.
 * Last upd:  03.03.20.
 * Last JavaDoc upd:  05.03.20.
 */
public class Config {
    private String path;
    private Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /** Загружает настройки из файла по пути path(path initialize. через конструктор).
     * Фильтруем через stream от комментариев и пустых строк.
     * Собираем в карту:
     * Тип настроек      - Ключ.
     * Значение настроек - Значение.
     */
    public void load() {
        List<String> fileLines = IOHelper.readFileToList(path, ArrayList::new);
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
}