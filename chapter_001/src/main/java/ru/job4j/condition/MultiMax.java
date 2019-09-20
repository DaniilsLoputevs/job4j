package ru.job4j.condition;

public class MultiMax {
    public int max(int first, int second, int third) {
        int result = (first > second && first > third) ? first : second;
        return result >= first && result >= third ? result : third;
    }
}

// Maybe it would be a static method??? -
// Every Test-methods will be shorter on 1 line. Will not create object for use test-method