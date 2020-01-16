package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {
    private int[] array;
    private int index = 0;

    public EvenIterator(int[] arr) {
        int[] buffer = new int[arr.length];
        int count = 0;
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



//    @Override
//    public boolean hasNext() {
//        return check() != -1;
//    }
//
//    @Override
//    public Object next() {
//        var result = check();
//        var condition = result != -1;
//
//        index = (condition) ? index + 1 : index;
//        return (condition) ? result : new NoSuchElementException();
//
//    }

//    private int check() {
//        var result = -1;
//
//        for (int i = index; i < this.array.length; i++) {
//            if (this.array[i] % 2 == 0) {
//                result = this.array[i];
//                break;
//            }
//        }
//        return result;
//    }


}
