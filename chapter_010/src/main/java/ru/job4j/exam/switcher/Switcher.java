package ru.job4j.exam.switcher;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Switcher<T> {
    private final Thread master;
    private final Thread slave;
    private final Object lock = new Object();

    private final AtomicBoolean choose = new AtomicBoolean(false);
    private final AtomicBoolean trigger = new AtomicBoolean(false);

    public Switcher(Consumer<T> masterTask, Consumer<T> slaveTask) {
        this.master = initThread(masterTask, (nothing) -> choose.get() && trigger.get());
        this.slave = initThread(slaveTask, (nothing) -> !choose.get() && !trigger.get());
    }

    private Thread initThread(Consumer<T> task, Predicate<Object> predicate) {
        return new Thread(() -> {
            while (true) {
                if (predicate.test(null)) {
                    synchronized (lock) {
                        lock.notify();
                        task.accept(null);
                        beautyWait(lock);
                    }
                }
            }
        });
    }

    /**
     * Master try >> true & false - switch to NON print mode
     * Master done >> true & true - Master print
     * <p>
     * Slave try >> false & true - switch to NON print mode
     * Slave done >> false & false - Slave print
     */
    public void tryMaster() {
        if (this.master.getState() == Thread.State.NEW) {
            this.master.start();
        }
        this.choose.set(true);
        this.trigger.set(false);
    }

    public void doneMaster() {
        this.choose.set(true);
        this.trigger.set(true);
    }

    public void trySlave() {
        if (this.slave.getState() == Thread.State.NEW) {
            this.slave.start();
        }
        this.choose.set(false);
        this.trigger.set(true);
    }

    public void doneSlave() {
        this.choose.set(false);
        this.trigger.set(false);
    }

    public void run() {
        while (true) {
            tryMaster();
            doneMaster();
            trySlave();
            doneSlave();
        }
    }

    public static void main(String[] args) {
        var temp = new Switcher<>(
                (nothing) -> System.out.println("Master A"),
                (nothing) -> System.out.println("Slave B")
        );
        temp.run();
    }

    private void beautyWait(Object obj) {
        try {
            obj.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void beautySleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}