package ru.job4j.tracker.actions;

import org.junit.Test;
import ru.job4j.tracker.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class FindByNameTest {
    private Tracker tracker = new TrackerSQL();
    private List<String> actualAnswer = new ArrayList<>();
    private Consumer<String> output = actualAnswer::add;

    private BaseAction action = new FindByName(1, "");
    private Item testItem = new Item("Запись от - actions[FindByName.execute()]");

    @Test
    public void actionFindByNameTest() {
        tracker.add(testItem);

        // Действие
        action.execute(
                new StubInput(new String[] {
                        testItem.getName()
                }),
                tracker,
                output);

        List<Item> temp = List.of(testItem);

        List<String> expected = temp.stream()
                .map(item -> String.format("%s %s", item.getId(), item.getName()))
                .collect(Collectors.toList());

        assertEquals(expected, actualAnswer);

        new TrackerSQLTest().cleanBaseTracker(testItem);
    }

}