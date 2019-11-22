package ru.job4j.compare;

import java.util.Comparator;

public class LengthComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return Integer.compare(
                o1.getName().length(),
                o2.getName().length()
        );
    }
}
