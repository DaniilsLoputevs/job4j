package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator<Integer> implements Iterator {
    private int[] array;
    private int index = 0;

    public EvenIterator(int[] arr) {
        int[] buffer = new int[arr.length];
        var count = 0;
        for (int num : arr) {
            if (num % 2 == 0) {
                buffer[count++] = num;
            }
        }
        this.array = Arrays.copyOf(buffer, count);
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }

}
