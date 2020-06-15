package ru.job4j.cache;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DefaultCacheMapTest {

    @Test
    public void load() {
        var temp = new DefaultCacheMap();
        temp.load("file_one.txt");
        var resultTwo = temp.getCacheContent("file_two.txt"); // extra load
        var expectedTwo = List.of(
                "eeeeeeeeeeeeee",
                "ffffffffffffffffffffffffff",
                "gggg",
                "hhhhhhhhhhhhhhhhhhhh"
        );
        assertEquals(expectedTwo, resultTwo);
        var resultOne = temp.getCacheContent("file_one.txt");
        var expectedOne = List.of(
                "aaaaaaaaaaaaaaaaaa",
                "bbbbbbbbbbbbbb",
                "cccccccccccccccccccccccc",
                "dddddddddddd"
        );
        assertEquals(expectedOne, resultOne);
    }
}