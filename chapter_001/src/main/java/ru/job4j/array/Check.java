package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = false;
        for (int i = 0; i < data.length; i++) {
            boolean compare = data[0];
            if (compare == data[i]) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }
}