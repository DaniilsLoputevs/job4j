package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<T>();
    private final int maxQueSize;

    public SimpleBlockingQueue() {
        this.maxQueSize = 32;
    }

    public SimpleBlockingQueue(int maxQueSize) {
        this.maxQueSize = maxQueSize;
    }

    public void offer(T value) {
        synchronized (this) {
            if (queue.size() == maxQueSize) {
                threadGoSleep();
            } else {
                info(" - Que +1");
                queue.add(value);
                this.notifyAll();
            }
        }
    }

    public T poll() {
        synchronized (this) {
            T rsl = null;
            if (queue.size() == 0) {
                threadGoSleep();
            } else {
                info(" - Que -1");
                rsl = queue.poll();
            }
            return rsl;
        }
    }

    public synchronized int size() {
        return this.queue.size();
    }

    private void info(String string) {
        System.out.println(Thread.currentThread().getName() + " - "
                + Thread.currentThread().getState() + string);
    }

    private void threadGoSleep() {
        try {
            System.out.println(Thread.currentThread().getName() + " - "
                    + Thread.currentThread().getState() + " - WAIT");
            this.wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
