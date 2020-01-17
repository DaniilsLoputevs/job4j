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
        for (int anArr : arr) {
            if (anArr % 2 == 0) {
                buffer[count] = anArr;
                count++;
            }
        }
        this.array = Arrays.copyOf(buffer, count);
    }

        @Override
    public boolean hasNext() {
        return check() != -1;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = check();
        index = (result != -1) ? index + 1 : index;
        return result;

    }

    private int check() {
        var result = -1;
        for (int i = index; i < this.array.length; i++) {
            if (this.array[i] % 2 == 0) {
                result = this.array[i];
                break;
            }
        }
        return result;
    }


    // Альтернатива
//    @Override
//    public boolean hasNext() {
//        return array.length > index;
//    }
//
//    // сдесь hasNext() т.к. я не хотел повторять код, а собирался писать тоже самое.
//    @Override
//    public Object next() {
//        return (hasNext()) ? array[index++] : new NoSuchElementException();
//    }


}
