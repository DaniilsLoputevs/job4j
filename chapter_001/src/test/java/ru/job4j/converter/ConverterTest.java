package ru.job4j.converter;

import org.junit.Assert;
import org.junit.Test;

public class ConverterTest {

    @Test
    public void rubleToEuro() {
        int in = 140;
        int expected = 2;
        int out = Converter.rubleToEuro(in);
        Assert.assertEquals(expected, out);
    }
    @Test
    public void rubleToDollar() {
        int in = 180;
        int expected = 3;
        int out = Converter.rubleToDollar(in);
        Assert.assertEquals(expected, out);
    }
    @Test
    public void euroToRuble() {
        int in = 4;
        int expected = 280;
        int out = Converter.euroToRuble(in);
        Assert.assertEquals(expected, out);
    }
    @Test
    public void euroToDollar() {
        int in = 90;
        int expected = 99;
        float out = Converter.euroToDollar(in);
        Assert.assertEquals(expected, out, 0.01); // How it working?
    }
}