package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    private Tracker tracker = new Tracker();

    /**
     * generateId()
     */
    @Test
    public void generateId() {
        String example = new Tracker().generateId();
        String test = new Tracker().generateId();
        assertThat(example.equals(test), is(false));
    }

    /**
     * add()
     */
    @Test
    public void add() {
        Item example = new Item("example");
        tracker.add(example);

        Item test = tracker.findById(example.getId());
        // сравниваем, что это одна и тажа заявка
        assertThat(example, is(test));
    }

    /**
     * replace()
     */
    @Test
    public void replace() {
        Item example = new Item("example");
        Item test = new Item("test");

        tracker.add(example);

        tracker.replace(example.getId(), test);

        // Проверяем, что заявка с таким id имеет новое имя "test"
        assertThat(tracker.findById(example.getId()).getName(), is("test"));
    }
    @Test
    public void replaceFail() {
        Item example = new Item("example");
        tracker.replace(example.getId(), new Item("test"));

        assertNull(tracker.findById(example.getId()));
    }

    /**
     * delete()
     */
    @Test
    public void delete() {
        Item first = new Item("one");
        Item second = new Item("two");
        tracker.add(first);
        tracker.add(second);

        tracker.delete(second.getId());

        ArrayList<Item> test = new ArrayList();
        test.add(first);

        assertThat(tracker.findAll(), is(test));
    }
    @Test
    public void deleteFail() {
        Item first = new Item("one");
        tracker.add(first);

        tracker.delete(tracker.generateId());

        ArrayList<Item> test = new ArrayList();
        test.add(first);

        assertThat(tracker.findAll(), is(test));
    }


    /**
     * findAll()
     */
    @Test
    public void findAll() {
        Item example = new Item("example");
        Item test = new Item("test");
        tracker.add(example);
        tracker.add(test);

        ArrayList<Item> itemsTest = new ArrayList();
        itemsTest.add(example);
        itemsTest.add(test);

        assertThat(tracker.findAll(), is(itemsTest));
    }

    /**
     * findByName()
     */
    @Test
    public void findByName() {
        Item first = new Item("example");
        Item second = new Item("test");
        Item third = new Item("example");

        tracker.add(first);
        tracker.add(second);
        tracker.add(third);

        ArrayList<Item> itemsTest = new ArrayList();
        itemsTest.add(first);
        itemsTest.add(third); // "example"
        assertThat(tracker.findByName("example"), is(itemsTest));
    }

    /**
     * findById()
     */
    @Test
    public void findById() {
        Item example = new Item("example");
        Item test = new Item("test");

        tracker.add(example);
        tracker.add(test);
        assertThat(tracker.findById(test.getId()), is(test));
    }

    @Test
    public void indexOfId() {
        Item first = new Item("example");
        Item second = new Item("test");

        tracker.add(first);
        tracker.add(second);

        assertThat(tracker.indexOfId(second.getId()), is(1));
    }

    @Test // Ручной тест
    public void toStringTest() {
        Item first = new Item("example");
        Item second = new Item("test");
        Item third = new Item("example");

        tracker.add(first);
        tracker.add(second);
        tracker.add(third);

        System.out.println(tracker.toString());
    }

    @Test
    public void contains() {
        Item first = new Item("example");
        tracker.add(first);
        Assert.assertTrue(tracker.containsId(first.getId()));
    }
}