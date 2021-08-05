package ru.job4j.nonblockalgoritm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CasCounterTest {
    @Test
    public void when3PushThen3Poll() {
        var counter = new CasCounter();
        var threadOne = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        counter.increment();
                    }
                }
        );
        var threadTwo = new Thread(
                () -> {
                    for (int i = 0; i < 20; i++) {
                        counter.increment();
                    }
                }
        );
        var threadThree = new Thread(
                () -> {
                    for (int i = 0; i < 30; i++) {
                        counter.increment();
                    }
                }
        );
        threadOne.start();
        threadTwo.start();
        threadThree.start();

        try {
            threadOne.join();
            threadTwo.join();
            threadThree.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(60, counter.get());
    }

}