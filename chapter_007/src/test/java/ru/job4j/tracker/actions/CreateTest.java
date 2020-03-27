package ru.job4j.tracker.actions;

import org.junit.Test;
import ru.job4j.tracker.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

//public class CreateTest extends BaseTest {
public class CreateTest {
    private Tracker tracker = new TrackerSQL();
    private List<String> actualAnswer = new ArrayList<>();
    private Consumer<String> output = actualAnswer::add;

    private BaseAction action = new Create(1, "");
//    private Item testItem = new Item("Запись от - actions[Create.execute()]");

    @Test
    public void actionCreateTest() {
        // Действие
        action.execute(
                new StubInput(new String[] {
                        "Запись от - actions[Create.execute()]"
                }),
                tracker,
                output);

        List<Item> result = tracker.findByName("Запись от - actions[Create.execute()]");

        List<String> expected = result.stream()
                .map(item -> String.format("%s %s", item.getId(), item.getName()))
                .collect(Collectors.toList());

        assertEquals(expected, actualAnswer);

        new TrackerSQLTest().cleanBaseTracker(result.get(0));
    }

//    @Test
//    public void baseTestCreate() {
//        this.formOne(action,
//                "Запись от - actions[Create.execute()]",
//                new String[] {
//                        "Запись от - actions[Create.execute()]"
//                },
//                "create"
//                );
//    }

}