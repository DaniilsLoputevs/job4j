package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(() -> {
        });
        Thread second = new Thread(() -> {
        });
        System.out.println("first state: " + first.getState());
        first.start();
        System.out.println("second state: " + second.getState());
        second.start();
        while (isThreadAlive(first) && isThreadAlive(second)) {
            System.out.println("first state: " + first.getState());
            System.out.println("second state: " + second.getState());
        }
        System.out.println("first state: " + first.getState());
        System.out.println("second state: " + second.getState());
    }

    private static boolean isThreadAlive(Thread thread) {
        return thread.getState() != Thread.State.TERMINATED;
    }
}
