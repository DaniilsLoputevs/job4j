package ru.job4j.condition;

public class Max {
    // max from N numbers
    public static int max(int left, int right) {
        return left > right ? left : right;
    }

    public static int max(int first, int second, int third) {
        int result = (first > second && first > third) ? first : second;
        return result >= first && result >= third ? result : third;
    }

    public static int max(int first, int second, int third, int fourth) {
        int result = (first > second) ? first : second;
        result = (result > third) ? result : third;
        return (result > fourth) ? result : fourth;
    }
}