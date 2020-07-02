package ru.job4j.waitnotify;

import org.junit.Test;

public class SimpleBlockingQueueTest {
    @Test
    public void test() {
        var queue = new SimpleBlockingQueue<Integer>(3);
        Thread producer = new Thread(
                () -> {
                    try {
                        queue.offer(2);
                        Thread.sleep(10000);
                        queue.offer(3);
                        Thread.sleep(10000);
                        queue.offer(4);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                "Producer"
        );
        Thread consumer = new Thread(
                () -> {
                    try {
                        queue.poll();
                        Thread.sleep(1000);
                        queue.poll();
                        Thread.sleep(1000);
                        queue.poll();
                        Thread.sleep(1000);
                        queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                "Consumer"
        );
        System.out.println("Program start");
        queue.offer(1);

        System.out.println(producer.getName() + " - STARTED");
        producer.start();
        System.out.println(consumer.getName() + " - STARTED");
        consumer.start();

        try {
            System.out.println("main - sleep - 1000 ms");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main - AWAKE");

        System.out.println("Main - join others");
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main - finish work");
        System.out.println("Program finish");
    }
    /*
    Program start
    main - RUNNABLE - Que +1
    Producer - STARTED
    Consumer - STARTED
    main - sleep - 1000 ms
    Producer - RUNNABLE - Que +1
    Consumer - RUNNABLE - Que -1
    Main - AWAKE
    Main - join others
    Consumer - RUNNABLE - Que -1
    Consumer - RUNNABLE - WAIT
    Producer - RUNNABLE - Que +1
    Consumer - RUNNABLE - Que -1
    Producer - RUNNABLE - Que +1
    main - finish work
    Program finish
     */
}