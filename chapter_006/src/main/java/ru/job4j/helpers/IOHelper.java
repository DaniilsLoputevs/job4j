package ru.job4j.helpers;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс содержащий универсальные и вспомогательные методы для работы с IO.
 */
public class IOHelper {
    /** Преобразовать файл в List и далее работать с List.
     * @param path - Путь файла.
     * @return List<String> Все строчки из файла.
     */
    public static List<String> readFileToList(String path) {
        List<String> fileLines = new LinkedList<>();
        try (var bufferedReader = new BufferedReader(new FileReader(path))) {
            fileLines = bufferedReader.lines().collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.out.println("IOHelper - read File to List!");
            e.printStackTrace();
        }
        return fileLines;
    }

    /** Записать List в файл по пути path
     * @param path - Путь запичи.
     * @param content - Содержимое.
     */
    public static void writeListToFile(String path, List<String> content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String contentLine : content) {
                writer.write(contentLine);
            }
        } catch (IOException e) {
            System.out.println("IOHelper - write List to File!");
            e.printStackTrace();
        }
    }


    /** Сравнивает содержимое файла с List ожидания
     * Метод написан для тестов.
     * @param sourcePath - исходник для сравнения.
     * @param expected - Ожидание.
     * @return true/false
     */
    public static boolean compareInfoFromFileWithList(String sourcePath, List expected) {
        List<String> fileLines = readFileToList(sourcePath);
        return fileLines.containsAll(expected);
    }

    /** Очистить файл под ноль.
     * @param sourcePath - путь файла.
     */
    public static void clearFile(String sourcePath) {
        IOHelper.writeListToFile(sourcePath, List.of(""));
    }

    /** Создать новый файл.
     * @param path Путь.
     */
    public static File createFile(String path, boolean makeDir) {
        File file = new File(path);
        if (makeDir) {
            file.mkdir();
        } else {
            IOHelper.clearFile(file.getPath());
        }
        return file;
    }

    /** Получить расширение файла.
     * @param file файл.
     * @return расширение.
     */
    public static String getExt(File file) {
        return file.getName().substring(file.getName().lastIndexOf(".") + 1);
    }

    /** Получить дерикторию в формате dir + \
     * Нужен для простоты кода.
     * @param file - файл.
     * @return dir + \
     */
    public static String getDir(File file) {
        return file.getParent() + "\\";
    }

    /** Получить имя родительской папки
     * Нужен для простоты кода.
     * @param file - файл.
     * @return parentName
     */
    public static String getParentName(File file) {
        var temp = file.getParent();
        return temp.substring(temp.lastIndexOf("\\") + 1);
    }
}
