package ru.job4j.search;

import ru.job4j.io.Helper;

import java.io.File;
import java.util.*;

/**
 * Этот алгоритм обхода в ширину.
 * В этом задании нужно написать метод, который возвращает список всех файлов с конкретным расширением.
 * Метод должен заходить во всех каталоги.
 * Для этого нужно использовать алгоритм обхода дерева в ширину.
 */
public class Search {

    /**
     * @param parent - это путь до каталога, с которого нужно осуществлять поиск.
     * @param exts - это расширения файлов, которые мы хотим получить.
     * @return List<File> подходящих хайлов.
     */
    List<File> files(String parent, List<String> exts) {
        var current = new File(parent);
        var base = new LinkedList<>(List.of(new File(parent)));
        var result = new HashSet<File>();
        if (current.isDirectory()) {
            base.add(current);
        }
        while (!base.isEmpty()) {
            current = base.removeFirst();
            if (current.isDirectory()) {
                var rightFiles = List.of(current.listFiles(file -> exts.contains(Helper.getExt(file))));
                result.addAll(rightFiles);
                base.addAll(Arrays.asList(current.listFiles()));
            }
        }
        return List.copyOf(result);
    }

}