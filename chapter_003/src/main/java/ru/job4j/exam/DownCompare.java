package ru.job4j.exam;

import java.util.Comparator;

public class DownCompare implements Comparator<String> {
              //            K2         K1
    @Override //            1     0    -1
    public int compare(String left, String right) {
        int result = 0;
        int minLength = Math.min(left.length(), right.length());
        for (int i = 0; i < minLength && result == 0; i++) {
            result = right.charAt(i) - left.charAt(i);
        }
        return result == 0 ? left.length() - right.length() : result;
    }
}