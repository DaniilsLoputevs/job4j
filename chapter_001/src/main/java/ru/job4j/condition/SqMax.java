package ru.job4j.condition;

public class SqMax {
    public static int max(int first, int second, int third, int forth) {
        int result = forth;

        if (first > second) {

            if (first > third) {

                if (first > forth) {
                    result = first;
                }
            }
        } else if (second > third) {

            if (second > forth) {
                result = second;
            }
        } else if (third > forth) {
            result = third;
        }
        return result;
    }
    // Сделал пустые строки что бы, легче прочитать и понять
    // Это можно написать 3 тернарными выражениями
    public static int max_v2(int first, int second, int third, int forth) {
        int step_one = (first >=second) ? first : second;
        int step_two = (third >=forth) ? third : forth;
        return step_one >=step_two ? step_one : step_two;
    }
}
