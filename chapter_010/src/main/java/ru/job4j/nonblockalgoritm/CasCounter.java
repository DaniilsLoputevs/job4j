package ru.job4j.nonblockalgoritm;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CasCounter {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int temp;
        do {
            temp = count.get();
        } while (!count.compareAndSet(temp, temp + 1));
    }

    public int get() {
        int temp;
        do {
            temp = count.get();
        } while (!count.compareAndSet(temp, temp));
        return temp;
    }

}