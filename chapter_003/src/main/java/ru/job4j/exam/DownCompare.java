package ru.job4j.exam;

import java.util.Comparator;

public class DownCompare implements Comparator<String> {
              //            K2         K1
    @Override //            1     0    -1
    public int compare(String left, String right) {
        int result;
        int compareC;
        int compareL;

        compareC = Character.compare(left.charAt(1), right.charAt(1));

        compareC = (left.length() == 6 && right.length() == 6 && compareC == 0)
                ? Character.compare(left.charAt(5), right.charAt(5)) : compareC;
        compareC = (left.length() == 11 && right.length() == 11 && compareC == 0)
                ? Character.compare(left.charAt(10), right.charAt(10)) : compareC;

        compareL = (left.length() > right.length()) ? 1 : -1;
        compareL = (left.length() == right.length()) ? 0 : compareL;

        result = (compareC == 0 && compareL == 1) ? 1 : -1;

        result = (compareC == 0 && compareL == 0) ? 0 : result;
        return result;
    }
}