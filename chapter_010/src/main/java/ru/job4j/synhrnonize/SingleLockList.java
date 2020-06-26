package ru.job4j.synhrnonize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DymArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final DymArrayList<T> insideList;

    public SingleLockList() {
        this.insideList = new DymArrayList<>(16);
    }

    public SingleLockList(DymArrayList<T> insideList) {
        this.insideList = insideList;
    }


    public synchronized void add(T value) {
        this.insideList.add(value);
    }

    public synchronized T get(int index) {
        return this.insideList.get(index);
    }

    public synchronized int size() {
        return this.insideList.getSize();
    }

    public synchronized DymArrayList<T> copy() {
        var rsl = new DymArrayList<T>(size());
        for (var obj : insideList) {
            rsl.add(obj);
        }
        return rsl;
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return new LocalIterator();
    }


    /**
     * fail-safe - iterator will not see any changes in collection after create.
     * snapshot at the begging and work with this copy.
     */
    private class LocalIterator implements Iterator<T> {
        private final DymArrayList<T> store;
        private int position = 0;
        private final int size;

        public LocalIterator() {
            this.store = copy();
            this.size = store.getSize();
        }

        @Override
        public boolean hasNext() {
            return size > position;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) store.get(position++);

        }
    }
}
