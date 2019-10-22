package ru.job4j.tracker.actions;

import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FindByNameActionTest {
    @Test
    public void whenCheckOutput() {
        Tracker tracker = new Tracker();
        Item item = new Item("fix bug");
        Item item1 = new Item("PC fix");
        Item item2 = new Item("create a new task");
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));

        FindByNameItem act = new FindByNameItem();
        act.execute(new StubInput(new String[] {"create a new task"}), tracker);

        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(item2.getId() + " " + item2.getName())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}
