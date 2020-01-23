package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIteratorSecond implements Iterator<Integer> {
    private int[] array;
    private int nextIndex = 0;

    public EvenIteratorSecond(int[] arr) {
        array = arr;
    }

    @Override
    public boolean hasNext() {
        // Проверка
        for (int i = nextIndex; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                break;
            }
            nextIndex++;
        }
        return array.length > nextIndex;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[nextIndex++];
    }
}
