package ru.job4j.archive;

import ru.job4j.helpers.IOHelper;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Класс для врхивации дерикторий.
 */
public class Zip {

    /** Рахивировать 1 файл. (не работает с директориями)
     * @param source - содержимое для архива.
     * @param target - файл, куда будет заарвивировано source
     */
    public void pack(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Архивировать директорию. (поиск по корню, директории)
     * Создаёт архив по пути zipPath.
     *
     * @param root - корнь директории.
     * @param exts - Set<String> расширения, что будут игнорироваться.
     * @param zipPath - название архива.
     */
    public void zipTo(File root, Set<String> exts, String zipPath) throws Exception {
        var targetPath = zipPath;
        try (var zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(targetPath)))) {
            addElement(zip, root, exts, root.getName());
        }
    }

    /** Добавить Файл в Создоваемый Zip
     * Проходит по всем файлам что есть в корне:
     * 1) фильтрует файлы по расширениям.
     * 2) добавляет файлы по их Пути(см. getCorrectFilePath() )
     * Таким образом, создаётся верная архитектура.
     ***Если file является директорией, то рекурсивно вызываем метод addElement()
     * @param zos - ZipOutputStream запись в Zip архив.
     * @param root - корень и след. папки.
     * @param exts - ИГНОРТРУЕМЫЕ расширения. (Предпологаеться исп. Set.of() )
     * @param rootName - Имя папки где создастья Zip архив. (Нужно для правильного распределения файлов по папкам.)
     * @throws IOException -
     */
    private void addElement(ZipOutputStream zos, File root, Set<String> exts, String rootName) throws IOException {
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                addElement(zos, file, exts, rootName);
                continue;
            }
            if (file.isFile() && !checkExts(file, exts)) {

                var fis = new FileInputStream(file);
                var filePath = getCorrectFilePath(file, rootName);
                zos.putNextEntry(new ZipEntry(filePath));

                byte[] buffer = new byte[4048];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fis.close();
            }
        }

    }

    /** Провекра: у файла подходящие расщирений.
     **** Вниамательно смотреть addElement().
     * @param file - файл на проверку.
     * @param exts - Set<String> расширения.
     * @return true/false.
     */
    private boolean checkExts(File file, Set<String> exts) {
        return exts.contains(IOHelper.getExt(file));
    }

    /** Находит превильный Path для создания фалй в Zip.
     * Исп. rootName, для создания Верного пути.
     * @param file - файл.
     * @param rootName - Имя папки где находиться Zip.
     * @return Путь создания файла.
     */
    private String getCorrectFilePath(File file, String rootName) {
        return file.getPath().substring(file.getParent().indexOf(rootName));
    }
}