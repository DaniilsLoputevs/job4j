package ru.job4j.condition;

public class MultiMax {
    public int max(int first, int second, int third) {
        if (first > second & first > third) {
            return first;
        } else if (second > first & second > third) {
            return second;
        } else return third;
    }
}

// Maybe it would be a static method??? -
// Every Test-methods will be shorter on 1 line. Will not create object for use test-method