package ru.job4j.copy;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArgsTest {
    private Args args;

    /* Изначальные параметы задачи:
     *  -d c:/ -n *.txt -m -o log.txt
     *    "-d",
     *    "c:/",
     *    "-n",
     *    "*.txt",
     *    "-o",
     *    "log.txt"
     */
    @Before
    public void setUp() {
        args = new Args(new String[] {
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
    public void rootPath() {
        var expected = "./src/test/java/ru/job4j/archive";
        assertThat(args.rootPath(), is(expected));
    }

    @Test
    public void findParamValue() {
        var expected = "test_copy_file.txt";
        assertThat(args.findParamValue(), is(expected));
    }

    @Test
    public void target() {
        var expected =  "./src/test/java/ru/job4j/copy" + "/log.txt";
        assertThat(args.target(), is(expected));
    }

}