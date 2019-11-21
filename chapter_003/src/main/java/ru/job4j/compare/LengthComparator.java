package ru.job4j.compare;

import java.util.Comparator;

public class LengthComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        int result = (o1.getName().length() > o2.getName().length()) ? 1 : -1;
        result = (o1.getName().length() == o2.getName().length()) ? 0 : result;
        return result;
    }

}
