package ru.job4j.threadpool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class ThreadPoolTest {
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
        System.out.println("### Start Program ###");
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(() -> {
            for (int i = 0; i <= 5; i++) {
                System.out.println("loading: " + i);
            }
        });
        threadPool.waitUntilFinishWork();
        threadPool.showAllThreadsState();
        threadPool.shutdown();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.showAllThreadsState();
        System.out.println("### Finish Program ###");

        // ### LOG ###
//        TestHelper.saveLog("src/test/java/ru/job4j/threadpool/logN.txt", newOutput.toString());
        // ### LOG ###

        assertTrue(newOutput.toString().contains("loading: 1"));
        assertTrue(newOutput.toString().contains("Thread pool: thread"));
        assertTrue(newOutput.toString().contains("Thread pool: shutdown"));
        assertTrue(newOutput.toString().contains("Thread pool: all threads state:"));
        assertTrue(newOutput.toString().contains("Thread pool: thread 1 - TERMINATED"));
    }

    /*
        ### Start Program ###
        main - RUNNABLE - Que +1
        Thread pool: thread 1 - RUNNABLE - Que -1
        loading: 0
        loading: 1
        loading: 2
        loading: 3
        loading: 4
        loading: 5
        Thread pool: thread 2 - RUNNABLE - WAIT
        Thread pool: thread 3 - RUNNABLE - WAIT
        Thread pool: thread 0 - RUNNABLE - WAIT
        Thread pool: thread 1 - RUNNABLE - WAIT
        Thread pool: all threads state:
        Thread pool: thread 0 - WAITING
        Thread pool: thread 1 - WAITING
        Thread pool: thread 2 - WAITING
        Thread pool: thread 3 - WAITING
        Thread pool: shutdown
        main - RUNNABLE - Que +1
        Thread pool: all threads state:
        Thread pool: thread 0 - TERMINATED
        Thread pool: thread 1 - TERMINATED
        Thread pool: thread 2 - TERMINATED
        Thread pool: thread 3 - TERMINATED
        ### Finish Program ###
    */

}