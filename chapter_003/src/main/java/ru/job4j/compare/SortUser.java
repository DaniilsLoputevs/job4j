package ru.job4j.compare;

import java.util.*;

public class SortUser {
    public static Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    /**
     * в этом методе необходимо определить
     * Comparator для метода Collections.sort и отсортировать List<User> по длине имени.
     */
    public static List<User> sortNameLength(List<User> list) {
        Comparator comparator = new LengthComparator();
        Collections.sort(list, comparator);
        return new ArrayList<>(list);
    }

    /**
     * в этом методе необходимо определить Comparator для метода Collections.sort и отсортировать
     * List<User> по обоим полям, сначала сортировка по имени в лексикографическом порядке, потом по возрасту.
     */
    public static List<User> sortByAllFields(List<User> list) {
        NameAndAgeComparator comparator = new NameAndAgeComparator();
        Collections.sort(list, comparator);
        return new ArrayList<>(list);
    }
}