package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

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
        Tracker tracker = new Tracker();
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
        Tracker tracker = new Tracker();
        Item example = new Item("example");
        Item test = new Item("test");

        tracker.add(example);

        tracker.replace(example.getId(), test);

        // Проверяем, что заявка с таким id имеет новое имя "test"
        assertThat(tracker.findById(example.getId()).getName(), is("test"));
    }
    @Test
    public void replaceFail() {
        Tracker tracker = new Tracker();
        Item example = new Item("example");
        Item test = new Item("test");

//        tracker.add(example);

        tracker.replace(example.getId(), test);

        // Проверяем, что заявка с таким id имеет новое имя "test"
        assertThat(tracker.findById(example.getId()).getName(), is("test"));
    }

    /**
     * delete()
     */
    @Test
    public void delete() {
        Tracker example = new Tracker();
        Item first = new Item("one");
        Item second = new Item("two");
        example.add(first);
        example.add(second);

        example.delete(second.getId());

        ArrayList<Item> test = new ArrayList();
        test.add(first);

        assertThat(example.findAll(), is(test));
    }
    @Test
    public void deleteFail() {
        Tracker example = new Tracker();
        Item first = new Item("one");
        Item second = new Item("two");
//        example.add(first);
        example.add(second);

        example.delete(second.getId());

        ArrayList<Item> test = new ArrayList();
        test.add(first);

        assertThat(example.findAll(), is(test));
    }


    /**
     * findAll()
     */
    @Test
    public void findAll() {
        Tracker tracker = new Tracker();
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
        Tracker tracker = new Tracker();
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
        Tracker tracker = new Tracker();
        Item example = new Item("example");
        Item test = new Item("test");

        tracker.add(example);
        tracker.add(test);
        assertThat(tracker.findById(test.getId()), is(test));
    }

    @Test
    public void indexOfId() {
        Tracker tracker = new Tracker();
        Item first = new Item("example");
        Item second = new Item("test");

        tracker.add(first);
        tracker.add(second);

        assertThat(tracker.indexOfId(second.getId()), is(1));
    }

    @Test // Ручной тест
    public void toStringTest() {
        Tracker tracker = new Tracker();
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
        Tracker tracker = new Tracker();
        Item first = new Item("example");
        tracker.add(first);
        Assert.assertTrue(tracker.containsId(first.getId()));
    }
}