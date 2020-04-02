package ru.job4j.copy02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgsTest {
    private Args args = new Args();

    @Before
    public void setUp() {
        args.load(new String[]{
                "-d",
                "./src/test/java/ru/job4j/archive",
                "-n",
                "-f",
                "test_copy_file.txt",
                "-o",
                "./src/test/java/ru/job4j/copy" + "/log.txt"
        });
    }

    @Test
    public void getDirectory() {
        var expected = "./src/test/java/ru/job4j/archive";
        assertEquals(expected, args.getDirectory());
    }

    @Test
    public void getKey() {
        var expected = "test_copy_file.txt";
        assertEquals(expected, args.get("-f"));
    }

    @Test
    public void getTarget() {
        var expected = "./src/test/java/ru/job4j/copy" + "/log.txt";
        assertEquals(expected, args.getTarget());
    }

}