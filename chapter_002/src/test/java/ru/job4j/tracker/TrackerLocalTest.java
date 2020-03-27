package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TrackerLocalTest {
    private TrackerLocal localTracker = new TrackerLocal();

    @Test
    public void generateId() {
        String example = new TrackerLocal().generateId();
        String test = new TrackerLocal().generateId();
        assertNotEquals(example, test);
    }

    @Test
    public void add() {
        Item example = new Item("example");
        localTracker.add(example);

        Item test = localTracker.findById(example.getId());
        // сравниваем, что это одна и тажа заявка
        assertEquals(test, example);
    }

    @Test
    public void replace() {
        Item example = new Item("example");
        Item test = new Item("test");

        localTracker.add(example);

        localTracker.replace(example.getId(), test);

        // Проверяем, что заявка с таким id имеет новое имя "test"
        assertEquals("test", localTracker.findById(example.getId()).getName());
    }
    @Test
    public void replaceFail() {
        Item example = new Item("example");
        localTracker.replace(example.getId(), new Item("test"));

        assertNull(localTracker.findById(example.getId()));
    }

    @Test
    public void delete() {
        Item first = new Item("one");
        Item second = new Item("two");
        localTracker.add(first);
        localTracker.add(second);

        localTracker.delete(second.getId());

        ArrayList<Item> test = new ArrayList<>();
        test.add(first);

        assertEquals(test, localTracker.findAll());
    }
    @Test
    public void deleteFail() {
        Item first = new Item("one");
        localTracker.add(first);

        localTracker.delete(localTracker.generateId());

        ArrayList<Item> test = new ArrayList<>();
        test.add(first);

        assertEquals(test, localTracker.findAll());
    }


    @Test
    public void findAll() {
        Item example = new Item("example");
        Item test = new Item("test");
        localTracker.add(example);
        localTracker.add(test);

        ArrayList<Item> itemsTest = new ArrayList<>();
        itemsTest.add(example);
        itemsTest.add(test);

        assertEquals(itemsTest, localTracker.findAll());
    }

    @Test
    public void findByName() {
        Item first = new Item("example");
        Item second = new Item("test");
        Item third = new Item("example");

        localTracker.add(first);
        localTracker.add(second);
        localTracker.add(third);

        ArrayList<Item> itemsTest = new ArrayList<>();
        itemsTest.add(first);
        itemsTest.add(third); // "example"
        assertEquals(itemsTest, localTracker.findByName("example"));
    }

    @Test
    public void findById() {
        Item example = new Item("example");
        Item test = new Item("test");

        localTracker.add(example);
        localTracker.add(test);
        assertEquals(test, localTracker.findById(test.getId()));
    }

    @Test
    public void indexOfId() {
        Item first = new Item("example");
        Item second = new Item("test");

        localTracker.add(first);
        localTracker.add(second);

        assertEquals(1, localTracker.indexOfId(second.getId()));
    }

    @Test
    public void toStringTest() {
        Item first = new Item("example");
        Item second = new Item("test");
        Item third = new Item("example");

        localTracker.add(first);
        localTracker.add(second);
        localTracker.add(third);

        assertEquals("TrackerLocal{items = example, test, example, }",
                localTracker.toString());
    }

    @Test
    public void contains() {
        Item first = new Item("example");
        localTracker.add(first);
        assertTrue(localTracker.containsId(first.getId()));
    }
}