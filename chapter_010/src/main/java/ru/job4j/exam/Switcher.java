package ru.job4j.exam;

public class Switcher {
    static final Object LOCK = new Object();
    static boolean runThreads = true;

    private static class ThreadExample extends Thread implements Runnable {
        private final String name;

        public ThreadExample(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (runThreads) {
                synchronized (LOCK) {
                    try {
                        LOCK.notify();
                        System.out.println(name);
                        Thread.sleep(1000);
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        var one = new ThreadExample("Thread A");
        var two = new ThreadExample("Thread B");
        one.start();
        two.start();

        try {
            Thread.sleep(20000);
            runThreads = false;
            Thread.sleep(2000);
            // one.getState() == TERMINATED
            // two.getState() == WAITING
            one.join();
            two.interrupt();
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}