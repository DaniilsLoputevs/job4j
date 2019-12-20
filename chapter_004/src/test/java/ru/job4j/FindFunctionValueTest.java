package ru.job4j;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FindFunctionValueTest {
    @Test
    public void linearFunction() {
        List<Double> result = FindFunctionValue.diapason(5, 8,
                x -> 2 * x + 1);

        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }
    @Test
    public void quadFunction() {
        List<Double> result = FindFunctionValue.diapason(2, 6,
                x ->  x * x);

        List<Double> expected = Arrays.asList(4D, 9D, 16D, 25D);
        assertThat(result, is(expected));
    }
    @Test
    public void logarifmFunction() {
        List<Double> result = FindFunctionValue.diapason(1, 3,
                Math::log);

        List<Double> expected = Arrays.asList(0D, 0.6931471805599453D);
        assertThat(result, is(expected));
    }

}
