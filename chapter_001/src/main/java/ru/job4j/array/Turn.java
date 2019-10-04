package ru.job4j.array;

public class Turn {
    // переворачивает массив с головы до ног
    public int[] back(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            int endIndex = (array.length - 1) - i;
            array[i] = array[endIndex];
            array[endIndex] = temp;
        }
        return array;
    }

}