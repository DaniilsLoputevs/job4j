package ru.job4j.exam;

import java.util.Comparator;

public class DownCompare implements Comparator<String> {
              //            K2         K1
    @Override //            1     0    -1
    public int compare(String left, String right) {
        // Constants
        final int firstCharIndex = 1;
        final int secondCharIndex = 5;
        final int thirdCharIndex = 10;
        // Ints
        // Compare char of Left && Right
        int compareC;
        // Compare length of Left && Right
        int compareL;
        // Final result
        int result;

        compareC = Character.compare(left.charAt(firstCharIndex), right.charAt(firstCharIndex));

        compareC = (left.length() == secondCharIndex + 1 && right.length() == secondCharIndex + 1 && compareC == 0)
                ? Character.compare(left.charAt(secondCharIndex), right.charAt(secondCharIndex)) : compareC;

        compareC = (left.length() == thirdCharIndex + 1 && right.length() == thirdCharIndex + 1 && compareC == 0)
                ? Character.compare(left.charAt(thirdCharIndex), right.charAt(thirdCharIndex)) : compareC;

        compareL = (left.length() > right.length()) ? 1 : -1;
        compareL = (left.length() == right.length()) ? 0 : compareL;

        result = (compareC == 0 && compareL == 1) ? 1 : -1;
        result = (compareC == 0 && compareL == 0) ? 0 : result;
        return result;
    }
}