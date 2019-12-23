package ru.job4j.exam;

import java.util.ArrayList;
import java.util.HashSet;
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


    public static Set<String> fillGaps(ArrayList<String> list) {
        HashSet<String> tmp = new HashSet<>(list);
        for (String value : list) {
            String start = "";
            for (String el : value.split("/")) {
                tmp.add(start + el);
                start += el + "/";
            }
        }
        return new HashSet<>(tmp);
    }

//    public static List<String> fillGaps(List<String> deps) {
//        HashSet<String> tmp = new HashSet<>();
//        for (String value : deps) {
//            String start = "";
//            for (String el : value.split("/")) {
//                // tmp.add start + "/" + el
//            }
//        }
//        return new ArrayList<>(tmp);
//    }

}
