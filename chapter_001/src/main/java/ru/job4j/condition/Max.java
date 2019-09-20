package ru.job4j.condition;

public class Max {
    public static int max(int left, int right) {
        return left > right ? left : right;
        // if both numbers are equals, result = one of them
    }
}