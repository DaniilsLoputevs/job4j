package ru.job4j.search;

import ru.job4j.helpers.IOHelper;

import java.io.File;
import java.util.*;

/** Класс для поиска файлов в Файловой Системе по его расширению.
 * Задача: Написать метод, который возвращает список всех файлов с конкретным расширением.
 * Метод должен заходить во всех каталоги,
 * для этого нужно использовать алгоритм обхода дерева в ширину.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 18.02.20.
 * Last upd:  05.03.20.
 * Last JavaDoc upd:  05.03.20.
 */
public class Search {

    /** Поиск файлов в Файловой Системе.
     * @param rootPath - Путь до каталога, с которого нужно осуществлять поиск.
     * @param exts -  расширения файлов, которые мы хотим получить.
     * @return List<File> подходящих файлов.
     */
    List<File> files(String rootPath, Set<String> exts) {
        var current = new File(rootPath);
        var base = new LinkedList<>(List.of(new File(rootPath)));
        var result = new HashSet<File>();

        if (current.isDirectory()) {
            base.add(current);
        }
        while (!base.isEmpty()) {
            current = base.removeFirst();
            if (current.isDirectory()) {
                result.addAll(Set.of(current.listFiles(file -> exts.contains(IOHelper.getExt(file)))));
                base.addAll(Arrays.asList(current.listFiles()));
            }
        }
        return List.copyOf(result);
    }

}