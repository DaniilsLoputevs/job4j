package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс содержащий универсальные и вспомогательные методы для IO.
 */
public class Helper {
    /** Преобразовать файл в List и далее работать с List.
     * @param path Путь файла.
     * @return List<String> Все строчки из файла.
     */
    public static List<String> readFileToList(String path) {
        List<String> fileLines = new LinkedList<>();
        try (var bufferedReader = new BufferedReader(new FileReader(path))) {
            fileLines = bufferedReader.lines().collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.out.println("IOException - something wrong!");
            e.printStackTrace();
        }
        return fileLines;
    }

    /** Записать List в файл по пути path
     * @param path Путь запичи.
     * @param content Содержимое.
     */
    public static void writeListToFile(String path, List<String> content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String contentLine : content) {
                writer.write(contentLine);
            }
        } catch (IOException e) {
            System.out.println("IOException - something wrong!");
            e.printStackTrace();
        }
    }


    /** Сравнивает содержимое файла с List ожидания
     * Метод написан для тестов.
     * @param sourcePath исходник для сравнения.
     * @param expected Ожидание.
     * @return true/false
     */
    public static boolean compareInfoFromFileWithList(String sourcePath, List expected) {
        List<String> fileLines = readFileToList(sourcePath);
        return fileLines.containsAll(expected);
    }
}
