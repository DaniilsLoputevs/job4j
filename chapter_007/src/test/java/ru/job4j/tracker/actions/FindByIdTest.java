package ru.job4j.tracker.actions;

import org.junit.Test;
import ru.job4j.tracker.*;

import java.util.ArrayList;
import java.util.List;

import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

//public class FindByIdTest extends BaseTest {
public class FindByIdTest {
    private Tracker tracker = new TrackerSQL();
    private List<String> actualAnswer = new ArrayList<>();
    private Consumer<String> output = actualAnswer::add;

    private BaseAction action = new FindById(1, "");
    private Item testItem = new Item("Запись от - actions[FindById.execute()]");

    @Test
    public void actionFindByIdTest() {
        tracker.add(testItem);

        // Действие
        action.execute(
                new StubInput(new String[] {
                        testItem.getId()
                }),
                tracker,
                output);

        List<Item> temp = List.of(testItem);

        List<String> expected = temp.stream()
                .map(item -> String.format("%s %s", item.getId(), item.getName()))
                .collect(Collectors.toList());

        assertEquals(actualAnswer, expected);

        new TrackerSQLTest().cleanBaseTracker(testItem);
    }


//    @Test
//    public void baseTestFindById() {
//        this.formOne(action,
//                "Запись от - actions[FindById.execute()]",
//                new String[] {
//                        "stubInput"
//                },
//                "findById"
//        );
//    }

}