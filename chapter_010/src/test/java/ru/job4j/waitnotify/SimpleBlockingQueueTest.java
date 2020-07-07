package ru.job4j.waitnotify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimpleBlockingQueueTest {
    private ByteArrayOutputStream newOutput;
    private PrintStream defaultOutput;

    @Before
    public void changeOutput() {
        newOutput = new ByteArrayOutputStream();
        defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
    }

    @After
    public void returnOutput() {
        System.setOut(defaultOutput);
    }

    @Test
    public void iterateTwoThreads() {
        var queue = new SimpleBlockingQueue<Integer>(3);
        Thread producer = new Thread(
                () -> {
                    try {
                        queue.offer(2);
                        Thread.sleep(500);
                        queue.offer(3);
                        Thread.sleep(500);
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
                        Thread.sleep(100);
                        queue.poll();
                        Thread.sleep(100);
                        queue.poll();
                        Thread.sleep(100);
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


        assertTrue(newOutput.toString().contains("main - RUNNABLE - Que +1"));
        assertTrue(newOutput.toString().contains("Producer - RUNNABLE - Que +1"));
        assertTrue(newOutput.toString().contains("Consumer - RUNNABLE - Que -1"));
        assertTrue(newOutput.toString().contains("Main - AWAKE"));
        assertTrue(newOutput.toString().contains("Main - join others"));
        assertTrue(newOutput.toString().contains("Consumer - RUNNABLE - WAIT"));
        assertTrue(newOutput.toString().contains("main - finish work"));
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

    @Test
    public void normalConfigurationOfThreads() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        var buffer = new ArrayList<Integer>(3);

        final Thread producer = new Thread(
                () -> {
                    for (int index = 1; index <= 3; index++) {
                        try {
                            System.out.println("producer - offer(): " + index);
                            queue.offer(index);
                            buffer.add(index);
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                    System.out.println("producer - FINISH ALL");
                }, "producer"

        );

        final Thread consumer = new Thread(
                () -> {
                    while (producer.isAlive()) {
                        try {
                            System.out.println("consumer - poll(): " + queue.poll());
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                    System.out.println("consumer - FINISH ALL");
                }, "consumer"
        );

        System.out.println("### Program Start ###");

        System.out.println(consumer.getName() + " - STARTED");
        consumer.start();
        System.out.println(producer.getName() + " - STARTED");
        producer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("### Program Finish ###");
        assertEquals(Arrays.asList(1, 2, 3), buffer);

        assertTrue(newOutput.toString().contains("consumer - STARTED"));
        assertTrue(newOutput.toString().contains("producer - offer(): 1"));
        assertTrue(newOutput.toString().contains("producer - RUNNABLE - Que +1"));
        assertTrue(newOutput.toString().contains("consumer - poll(): null"));
        assertTrue(newOutput.toString().contains("producer - offer(): 2"));
        assertTrue(newOutput.toString().contains("consumer - poll(): 1"));
        assertTrue(newOutput.toString().contains("producer - FINISH ALL"));
    }
    /*
    ### Program Start ###
    consumer - STARTED
    producer - STARTED
    producer - offer(): 1
    consumer - RUNNABLE - WAIT
    producer - RUNNABLE - Que +1
    consumer - poll(): null
    producer - offer(): 2
    producer - RUNNABLE - Que +1
    producer - offer(): 3
    producer - RUNNABLE - Que +1
    consumer - RUNNABLE - Que -1
    consumer - poll(): 1
    producer - FINISH ALL
    consumer - FINISH ALL
    ### Program Finish ###
     */
}