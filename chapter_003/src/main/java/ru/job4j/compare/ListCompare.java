package ru.job4j.compare;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int forCount = Math.min(left.length(), right.length());
        int result = 0;
        int lengthCompare = Integer.compare(left.length(), right.length());
        for (int i = 0; i < forCount; i++) {
            result = Character.compare(left.charAt(i), right.charAt(i));
            if (result != 0) {
                break;
            }
        }
        return ( result == 0 && lengthCompare != 0 ) ? lengthCompare : result;
    }
}