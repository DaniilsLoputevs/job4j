package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FitnessTest {
    @Test  // Ivan > Nik = 0
    public void whenIvanGreatNik() {
        Fitness fit = new Fitness();
        int days = fit.calc_one(95, 90);
        assertThat(days, is(0));
    }
    @Test  // Ivan > Nik = 1
    public void whenIvanLessByOneNik() {
        Fitness fit = new Fitness();
        int days = fit.calc_one(90, 95);
        assertThat(days, is(1));
    }
    @Test  // Ivan > Nik = 2
    public void whenIvanLessByFewNik() {
        Fitness fit = new Fitness();
        int days = fit.calc_one(50, 90);
        assertThat(days, is(2));
    }
    @Test  // Ivan > Nik = 3
    public void whenIvanLessByThreeNik() {
        Fitness fit = new Fitness();
        int days = fit.calc_one(20, 50);
        assertThat(days, is(3));
    }
}