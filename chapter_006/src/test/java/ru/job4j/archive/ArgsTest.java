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
                "c:/Danik/job4j",
                "-e",
                "*.java",
                "-o",
                "project.zip"
        });
    }

    @Test
    public void directory() {
        var expected = "c:/Danik/job4j";
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
        var expected = "project.zip";
        assertThat(args.output(), is(expected));
    }

}