package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DymArrayList<E> implements Iterable<E> {
    private Object[] container;
    private int size = 0;
    private int modCount = 0;

    public DymArrayList(int size) {
        this.container = new Object[size];
    }

    public void add(E value) {
        this.container[size++] = value;
        modCount++;
        if (container.length == size - 1) {
            reSize();
        }
    }

    public E get(int index) {
        return (E) this.container[index];
    }

    public int getSize() {
        return this.size;
    }

    public boolean contains(E value) {
        var result = false;
        for (int i = 0; i < this.size; i++) {
            if (container[i].equals(value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new LocalIterator();
    }

    private void reSize() {
        this.container = Arrays.copyOf(container, container.length * 2);
    }


    /**
     * fail-fast - iterator will throw ConcurrentModificationException
     * if collection will change while iterator exist.
     */
    private class LocalIterator implements Iterator<E> {
        private final int expectedModCount = modCount;
        private int position = 0;

        @Override
        public boolean hasNext() {
            checkForModification();
            return size > position;
        }

        @Override
        public E next() {
            checkForModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) container[position++];
        }

        void checkForModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }


}