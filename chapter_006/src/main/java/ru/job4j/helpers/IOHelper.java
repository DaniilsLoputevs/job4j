package ru.job4j.helpers;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/** Класс содержащий универсальные и вспомогательные методы для работы с IO.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 18.02.20.
 * Last upd:  06.03.20.
 * Last JavaDoc upd:  06.03.20.
 */
public class IOHelper {

    /** Преобразовать содержимое файла в List, далее работать текстом в виде List.
     * @param path - Путь файла.
     * @return List<String> Все строчки из файла.
     */
    public static List<String> readFileToList(String path) {
        List<String> fileLines = new LinkedList<>();
        try (var bufferedReader = new BufferedReader(new FileReader(path))) {
            fileLines = bufferedReader.lines().collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.out.println("IOException: IOHelper - read File to List!");
            e.printStackTrace();
        }
        return fileLines;
    }

    /** Записать List в файл{@code path}.
     * @param path - Путь записи.
     * @param content - List для записи.
     */
    public static void writeListToFile(String path, List<String> content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String contentLine : content) {
                writer.write(contentLine + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("IOException: IOHelper - write List to File!");
            e.printStackTrace();
        }
    }

    /** Переписать Текст из {@code source} в файл по {@code targetPath}.
     * @param source - Оригинал.
     * @param targetPath - Копия.
     */
    public static void writeFileToFile(File source, String targetPath) {
        writeListToFile(targetPath, readFileToList(source.getPath()));
    }

    /** Сравнивает текстовое содержимое из файла{@code sourcePath} с {@code List expected}.
     * @param sourcePath - Путь к файлу для сравнения.
     * @param expected - Ожидаемое содержимое.
     * @return true/false
     */
    public static boolean compareInfoFromFileWithList(String sourcePath, List expected) {
        List<String> fileLines = readFileToList(sourcePath);
        return fileLines.containsAll(expected);
    }

    /** Полностью очистить текстовой файл.
     * @param sourcePath - путь файла.
     */
    public static void clearFile(String sourcePath) {
        IOHelper.writeListToFile(sourcePath, List.of(""));
    }

    /** Создать новый файл.
     ** Можно сразу сделать его директорией.
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

    /** Получить имя родительской папки.
     * Нужен для простоты кода.
     * @param file - файл.
     * @return имя родительской папки.
     */
    public static String getParentName(File file) {
        var temp = file.getParent();
        return temp.substring(temp.lastIndexOf("\\") + 1);
    }
}
