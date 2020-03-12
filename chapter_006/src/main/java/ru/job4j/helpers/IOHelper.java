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
 * Last upd:  12.03.20.
 * Last JavaDoc upd:  12.03.20.
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
     ** Если не нужно разделять строки >> sysSeparator = "";
     * @param path - Путь записи.
     * @param content - List для записи.
     * @param sysSeparator - Разделитель строки.
     */
    public static void writeListToFile(String path, List<String> content, String sysSeparator) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String contentLine : content) {
                writer.write(contentLine + sysSeparator);
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
    public static void copyTextToTarget(File source, String targetPath, String sysSeparator) {
        writeListToFile(targetPath, readFileToList(source.getPath()), sysSeparator);
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
        IOHelper.writeListToFile(sourcePath, List.of(""), "");
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
}
