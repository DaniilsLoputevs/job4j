package ru.job4j.design.isp;

import daniils.StringHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class MenuManagerTest {
    private ByteArrayOutputStream outArray = new ByteArrayOutputStream();
    private PrintStream myOut = new PrintStream(outArray);

    @Before
    public void before() {
        System.setOut(myOut);
    }

    @After
    public void after() {
        System.setOut(System.out);
    }

    @Test
    public void menuShowTest() {
        MenuManager fullMenu = new MenuManagerDefault(
                new MenuItem("Main Dir", List.of(
                        new MenuItem("root 1", List.of(
                                new MenuItem("leave 1-1", List.of(
                                        new MenuItem("leave-leave 1-A"),
                                        new MenuItem("leave-leave 1-B"))),
                                new MenuItem("leave 1-2"),
                                new MenuItem("leave 1-3"))),
                        new MenuItem("root 2", List.of(
                                new MenuItem("leave 2-1", List.of(
                                        new MenuItem("leave-leave 2-A"),
                                        new MenuItem("leave-leave 2-B"))),
                                new MenuItem("leave 2-2", List.of(
                                        new MenuItem("leave-leave 2-C"),
                                        new MenuItem("leave-leave 2-D")
                                ))
                        ))
                ))
        );
        fullMenu.showMenu();
        var expected = StringHelper.separateAndMerge(List.of(
                "Menu",
                "-- root 1",
                "---- leave 1-1",
                "-------- leave-leave 1-A",
                "-------- leave-leave 1-B",
                "---- leave 1-2",
                "---- leave 1-3",
                "-- root 2",
                "---- leave 2-1",
                "-------- leave-leave 2-A",
                "-------- leave-leave 2-B",
                "---- leave 2-2",
                "-------- leave-leave 2-C",
                "-------- leave-leave 2-D"
        ));
        Assert.assertEquals(expected, outArray.toString());
    }
}