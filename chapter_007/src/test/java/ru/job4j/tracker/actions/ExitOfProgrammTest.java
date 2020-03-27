package ru.job4j.tracker.actions;

import org.junit.Test;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.TrackerLocal;
import ru.job4j.tracker.TrackerSQL;

import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ExitOfProgrammTest {
    private Consumer<String> output = System.out::println;

    @Test
    public void actionExitOfProgrammTest() {
        boolean expect = new ExitOfProgramm(1, "").execute(
                new StubInput(new String[] {" "}), new TrackerSQL(), output);
        assertThat(expect, is(false));
    }
}