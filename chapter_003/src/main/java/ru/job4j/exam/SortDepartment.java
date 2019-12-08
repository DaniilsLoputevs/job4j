package ru.job4j.exam;

import java.util.Set;
import java.util.TreeSet;

public class SortDepartment {

    public static Set<String> sortDown(Set<String> treeSet) {
        TreeSet<String> result = new TreeSet<>(new DownCompare());
        result.addAll(treeSet);
        return result;
    }
}
