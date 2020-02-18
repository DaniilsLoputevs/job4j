package ru.job4j.io;

import java.util.LinkedList;
import java.util.List;

/** Анализ доступности сервера.
 * Задача: Чситать содержимое файла analise_input, проанализировать когда сервер работал.
 * (статус 400 || 500) и записать в файл analise_answers, зарение сделав понятное оформление.
 */
public class Analizy {
    /** Анализ времени работы сервера.
     * Считывает по пути source и записывает в файл по пути target.
     * Делает понятное оформление текста для послед. записи.
     * @param source Путь источника.
     * @param target Путь для запичи.
     */
    public void unavailable(String source, String target) {
//        запись в файл лучше перенести в отдельный метод
//        то что напарсили собрать в лист
//        сейчас ваш поток записи большее время ждет второй звезды и простаивает
//        а держать его просто так открытым плохая идея


        List<String> fileLines = Helper.readFileToList(source);
        List<String> fileContent = new LinkedList<>();

        var workTime = true;
        for (String line: fileLines) {
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
        Helper.writeListToFile(target, fileContent);
    }
}