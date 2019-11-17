package ru.job4j.sort;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MachineTest {

    @Test
    public void exp0() {
        Machine machine = new Machine();
        int[] expected = {};
        int[] rsl = machine.change(100, 100);
        assertThat(rsl, is(expected));
    }

    @Test
    public void exp15() {
        Machine machine = new Machine();
        int[] expected = {10, 5};
        int[] rsl = machine.change(50, 35);
        assertThat(rsl, is(expected));
    }

    @Test
    public void exp9() {
        Machine machine = new Machine();
        int[] expected = {5, 2, 2};
        int[] rsl = machine.change(30, 21);
        assertThat(rsl, is(expected));
    }

    @Test
    public void exp18() {
        Machine machine = new Machine();
        int[] expected = {10, 5, 2, 1};
        int[] rsl = machine.change(20, 2);
        assertThat(rsl, is(expected));
    }
}