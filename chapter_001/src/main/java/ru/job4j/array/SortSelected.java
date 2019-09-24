package ru.job4j.array;

public class SortSelected {
    // sort int[] by increase
    public static int[] sort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int temp = data[i];
            int minElement = findMinFrom(data,i);
            int indexMinEl = FindLoop.indexOf(data, minElement, i, data.length);
            data[i] = minElement;
            data[indexMinEl] = temp;
        }
        return data;
    }

    // find value of min element in int[]
    private static int findMinFrom (int[] data, int start) {
        int min = data[start];
        for (int i = start; i < data.length; i++) {
            if (min > data[i]) {
                min = data[i];
            }
        }
        return min;
    }
}