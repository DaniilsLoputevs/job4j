package ru.job4j.tracker;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

    /**
     * delete()
     */
    @Test
    public void delete() {
        Tracker example = new Tracker();
        Item first = new Item("one");
        Item second = new Item("two");
        Item third = new Item("three");
        example.add(first);
        example.add(second);
        example.add(third);

        example.delete(second.getId());

        ArrayList<Item> test = new ArrayList();
        test.add(first);
        test.add(third);

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

    // Тест Валидации
    @Test
    public void findByIdFail() {
        // Подгатовка
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        PrintStream defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
        // Основной блок
        Tracker tracker = new Tracker();
        Item example = new Item("example");
        tracker.add(example);
        // Действие
        tracker.findById(tracker.generateId());
        // Сравнение
        MatcherAssert.assertThat(
                newOutput.toString(),
                is(String.format("Такого id не существует.%n"))
        );
        // Возвращаем стандартный вывод
        System.setOut(defaultOutput);
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

}