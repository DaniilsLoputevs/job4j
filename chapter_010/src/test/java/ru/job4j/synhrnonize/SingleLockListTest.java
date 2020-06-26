package ru.job4j.synhrnonize;

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class SingleLockListTest {
    private final SingleLockList<Integer> list = new SingleLockList<>();

    @Test
    public void add() throws InterruptedException {
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));

        first.start();
        second.start();

        first.join();
        second.join();

        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);

        assertEquals(Set.of(1, 2), rsl);
    }

    /**
     * last assert - is assertFalse!
     */
    @Test
    public void iteratorTest() {
        List.of(3, 4).forEach(list::add);
        var iterator = list.iterator();
        list.add(5);
        assertTrue(iterator.hasNext());
        assertEquals(3, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(4, (int) iterator.next());
        assertFalse(iterator.hasNext());
    }
}