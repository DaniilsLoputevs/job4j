package ru.job4j.compare;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {
    public static Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }
}
