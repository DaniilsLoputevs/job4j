package ru.job4j.tracker.actions;

import org.junit.Test;
import ru.job4j.tracker.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ReplaceTest { private Tracker tracker = new TrackerSQL();
    private List<String> actualAnswer = new ArrayList<>();
    private Consumer<String> output = actualAnswer::add;

    private BaseAction action = new Replace(1, "");
    private Item testItem = new Item("Запись от - actions[Replace.execute()]");

    @Test
    public void actionReplaceTest() {
        testItem = tracker.add(testItem);
        var newId = testItem.getId();

        // Действие
        action.execute(
                new StubInput(new String[] {
                       testItem.getId(),
                        "Запись от - actions[Replace.execute()] - замена"
                }),
                tracker,
                output);

        List<Item> temp = List.of(new Item(newId, "Запись от - actions[Replace.execute()] - замена"));

        List<String> expected = temp.stream()
                .map(item -> String.format("%s %s", item.getId(), item.getName()))
                .collect(Collectors.toList());

        assertEquals(expected, actualAnswer);

        new TrackerSQLTest().cleanBaseTracker(testItem);
    }

}
