package ru.job4j.condition;

public class MultiMax {
    // max from 3 numbers
    public int max(int first, int second, int third) {
        int result = (first > second && first > third) ? first : second;
        return result >= first && result >= third ? result : third;
    }
}
