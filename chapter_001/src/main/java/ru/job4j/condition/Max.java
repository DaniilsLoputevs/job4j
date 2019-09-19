package ru.job4j.condition;

public class Max {
    public static int max(int left, int right) {
        int result;
        if (left > right) {
            result = left;
        } else {
            result = right;
        }
        // if both numbers are equals, result = one of them
        return result;
    }
}