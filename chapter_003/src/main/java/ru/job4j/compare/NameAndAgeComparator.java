package ru.job4j.compare;

import java.util.Comparator;

public class NameAndAgeComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        int rsl = o1.getName().compareTo(o2.getName());
        return rsl != 0 ? rsl : Integer.compare(o1.getAge(), o2.getAge());
    }
}