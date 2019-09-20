package ru.job4j.loop;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {
    @Test  // факториал для числа 5 равен 120
    public void Factorial_Five() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        assertThat(result, is(120));
    }
    @Test  // факториал для числа 0 равен 1
    public void Factorial_Zero() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(0);
        assertThat(result, is(0));
    }
    @Test  // факториал для числа 7 равен 5040
    public void Factorial_Seven() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(7);
        assertThat(result, is(5040));
    }
}
// Слишком длинные названия методов
// whenCalculateFactorialForFiveThenOneHundreedTwenty - Оечатка Hundreed >> Hundred
