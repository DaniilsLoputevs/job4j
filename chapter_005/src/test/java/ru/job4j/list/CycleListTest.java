package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

public class CycleListTest {

    @Test
    public void hasCycle() {

        CycleList first = new CycleList();
        CycleList two = new CycleList();
        CycleList third = new CycleList();
        CycleList four = new CycleList();

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        Assert.assertTrue(CycleList.hasCycle(first));
    }

}