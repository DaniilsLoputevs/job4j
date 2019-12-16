package ru.job4j.exam;

import java.util.Set;
import java.util.TreeSet;

/**
 * Класс для сортировки Департаментов
 ** По умолчанию сортировка по возрастанию
 */
public class SortDepartment {
    /**
     * Сортировка Департаментов по уменьшения
     */
    public static Set<String> sortDown(Set<String> treeSet) {
        TreeSet<String> result = new TreeSet<>(new DownCompare());
        result.addAll(treeSet);
        return result;
    }
}
