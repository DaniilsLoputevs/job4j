package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {
    private int[] array;
    private int index = 0;

    public EvenIterator(int[] arr) {
        int[] buffer = new int[arr.length];
        var count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                buffer[count] = arr[i];
                count++;
            }
        }
        this.array = Arrays.copyOf(buffer, count);
    }


    @Override
    public boolean hasNext() {
        return array.length > index;
    }

    // сдесь hasNext() т.к. я не хотел повторять код, а собирался писать тоже самое.
    @Override
    public Object next() {
        return (hasNext()) ? array[index++] : new NoSuchElementException();
    }
}
