package ru.job4j.archive;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArgsTest {
    private Args args;

    @Before
    public void setUp() {
        args = new Args(new String[] {
                "-d",
                "./src/test/java/ru/job4j/archive",
                "-e",
                "*.java",
                "-o",
                "src/test/java/ru/job4j/copy/log.txt"
        });
    }

    @Test
    public void directory() {
        var expected =  "./src/test/java/ru/job4j/archive";
        System.out.println(args.directory());
        System.out.println(expected);
        assertThat(args.directory(), is(expected));
    }

    @Test
    public void excule() {
        var expected = new HashSet<String>();
        expected.add("*.java");
        assertThat(args.excule(), is(expected));
    }

    @Test
    public void output() {
        var expected = "src/test/java/ru/job4j/copy/log.txt";
        assertThat(args.output(), is(expected));
    }

}