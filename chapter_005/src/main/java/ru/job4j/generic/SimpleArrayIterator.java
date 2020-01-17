package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator {

    private Object[] objects;
    private int index = 0;

    public SimpleArrayIterator(T[] objects) {
        this.objects = objects;
    }

    @Override
    public boolean hasNext() {
        return objects.length > index;
    }
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (T) this.objects[this.index++];
    }
}