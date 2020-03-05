package ru.job4j.archive;

import ru.job4j.helpers.IOHelper;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Класс для архивации дерикторий.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 27.02.20.
 * Last upd:  05.03.20.
 * Last JavaDoc upd:  05.03.20.
 */
public class Zip {

    /** Рахивировать 1 файл. (не работает с директориями)
     * @param source - содержимое для архива.
     * @param target - указание куда будет заархивирован source.
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
     * @param root - Корнь архивируемой директории.
     * @param exts - Set<String> Игнорируемые расширения.
     * @param zipPath - Пусть где будет создан архив.
     */
    public void zipTo(File root, Set<String> exts, String zipPath) throws Exception {
        try (var zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipPath)))) {
            addElement(zip, root, exts, root.getName());
        }
    }

    /** Добавить Файл в Создоваемый Zip
     ** Проходит по всем файлам что есть в корне:
     * 1) фильтрует файлы по расширениям.
     * 2) добавляет файлы по их Пути(см. getCorrectFilePath() )
     * Таким образом, создаётся верная архитектура. (файлы с пвпками в своем Path, создают все папки из директории)
     *** Если file является директорией, то рекурсивно вызывается метод addElement()
     *
     * @param zos - ZipOutputStream запись в Zip архив.
     * @param root - корень и след. папки.
     * @param exts - ИГНОРИРУЕМЫЕ расширения. (Предпологаеться исп. Set.of() )
     * @param rootName - Имя папки где создастся Zip архив. (Нужно для правильного распределения файлов по папкам)
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
     *** Вниамательно смотреть addElement().
     * @param file - Файл на проверку.
     * @param exts - Set<String> Подходящие расширения.
     * @return true/false.
     */
    private boolean checkExts(File file, Set<String> exts) {
        return exts.contains(IOHelper.getExt(file));
    }

    /** Задать правильный Path для создания файл в Zip архиве.
     *** Исп. rootName, для создания Верного пути.
     * @param file - Файл.
     * @param rootName - Имя папки где находиться Zip.
     * @return Путь создания файла.
     */
    private String getCorrectFilePath(File file, String rootName) {
        return file.getPath().substring(file.getParent().indexOf(rootName));
    }
}