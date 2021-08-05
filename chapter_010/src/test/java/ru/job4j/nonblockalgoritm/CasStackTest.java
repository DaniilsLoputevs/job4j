package ru.job4j.nonblockalgoritm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CasStackTest {
    @Test
    public void when3PushThen3Poll() {
        var stack = new CasStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.poll());
        assertEquals(2, stack.poll());
        assertEquals(1, stack.poll());
    }

    @Test
    public void when1PushThen1Poll() {
        var stack = new CasStack<>();
        stack.push(1);
        assertEquals(1, stack.poll());
    }

    @Test
    public void when2PushThen2Poll() {
        var stack = new CasStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.poll());
        assertEquals(1, stack.poll());
    }

}