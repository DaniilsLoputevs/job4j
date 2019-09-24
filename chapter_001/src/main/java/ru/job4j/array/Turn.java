package ru.job4j.array;

public class Turn {
    public int[] back(int[] array) {
        int temp = 0;
        int endIndex = 0;
        for (int i = 0; i < array.length; i++) {
            temp = array[i];
            endIndex = (array.length - 1) - i;
            if (i > endIndex) {
                break;
            }
            array[i] = array[endIndex];
            array[endIndex] = temp;
        }
        return array;
    }

}