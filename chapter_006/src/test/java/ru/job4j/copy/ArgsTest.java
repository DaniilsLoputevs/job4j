package ru.job4j.copy;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArgsTest {
    private Args args;

    @Before
    public void setUp() {
        args = new Args(new String[] {
                "-d",
                "c:/",
                "-n",
                "*.txt",
                "-o",
                "log.txt"
        });
//        -d c:/ -n *.txt -m -o log.txt
    }

    @Test
    public void directory() {
        var expected = "c:/";
        assertThat(args.rootPath(), is(expected));
    }

    @Test
    public void excule() {
        var expected = "*.txt";
        assertThat(args.findParamValue(), is(expected));
    }

    @Test
    public void output() {
        var expected = "log.txt";
        assertThat(args.target(), is(expected));
    }

}