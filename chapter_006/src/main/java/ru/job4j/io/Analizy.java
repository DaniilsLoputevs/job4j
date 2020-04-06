package ru.job4j.io;

import ru.job4j.helpers.IOHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Анализ доступности сервера.
 * Задача: Считать содержимое файла analise_input, проанализировать когда сервер работал.
 * Если статус = 400 || 500 >> сервер работаел.
 * Результат записать в файл analise_answers, зарение сделав понятное оформление.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 16.02.20.
 * Last upd:  03.03.20.
 * Last JavaDoc upd:  05.03.20.
 */
public class Analizy {

    /**
     * Анализ времени работы сервера.
     * Считывает по пути source текст и записывает в файл по пути target.
     * Делает понятное оформление текста для записи.
     *
     * @param source - Путь источника.
     * @param target - Путь для записи.
     */
    public void unavailable(String source, String target) {
        List<String> fileLines = IOHelper.readFileToList(source, ArrayList::new);
        List<String> fileContent = new ArrayList<>();

        var workTime = true;
        for (String line : fileLines) {
            if (workTime && line.startsWith("4") || line.startsWith("5")) {
                fileContent.add(line.substring(line.indexOf(" ") + 1));
                fileContent.add(" - ");
                workTime = false;
            } else if (!workTime && line.startsWith("2") || line.startsWith("3")) {
                fileContent.add(line.substring(line.indexOf(" ") + 1));
                fileContent.add("\n");
                workTime = true;
            }
        }
        IOHelper.writeListToFile(target, fileContent, "");
    }
}