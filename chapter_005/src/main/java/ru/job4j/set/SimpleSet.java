package ru.job4j.set;

import ru.job4j.list.DymArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private DymArrayList<E> container;
    private int size = 0;

    public SimpleSet(int length) {
        this.container = new DymArrayList<>(length);
    }

    public void add(E value) {
        if (size == 0 || !container.contains(value)) {
            container.add(value);
            size++;
        }
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
