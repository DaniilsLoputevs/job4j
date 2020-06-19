package ru.job4j.cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DefaultCacheMapTest {
    private ByteArrayOutputStream newOutput;
    private PrintStream defaultOutput;

    @Before
    public void changeOutput() {
        newOutput = new ByteArrayOutputStream();
        defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
    }

    @After
    public void returnOutput() {
        System.setOut(defaultOutput);
    }

    @Test
    public void load() {
        var cacheMap = new DefaultCacheMap();
        cacheMap.load("file_one.txt");

        var resultOne = cacheMap.getCacheContent("file_one.txt");
        var expectedOne = List.of(
                "aaaaaaaaaaaaaaaaaa",
                "bbbbbbbbbbbbbb",
                "cccccccccccccccccccccccc",
                "dddddddddddd"
        );
        assertEquals(expectedOne, resultOne);

        var resultTwo = cacheMap.getCacheContent("file_two.txt"); // extra load
        var expectedTwo = List.of(
                "eeeeeeeeeeeeee",
                "ffffffffffffffffffffffffff",
                "gggg",
                "hhhhhhhhhhhhhhhhhhhh"
        );
        assertEquals(expectedTwo, resultTwo);

        var resultThree = cacheMap.getCacheContent("file_not_exists.txt"); // extra load
        var expectedThree = List.of();
        assertEquals(expectedThree, resultThree);
        assertEquals(
                String.format("WRONG: Cache's directory doesn't contains file with this name: file_not_exists.txt%n"),
                newOutput.toString()
        );
    }
}