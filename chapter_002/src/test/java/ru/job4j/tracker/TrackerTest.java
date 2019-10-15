package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test // add
    public void add() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }
    @Test // replace
    public void replace() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1");
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2");
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
    @Test // delete
    public void delete() {
        Tracker tracker = new Tracker();
        Item first = new Item("one");
        Item second = new Item("two");
        Item third = new Item("three");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        assertThat(tracker.delete(second.getId()), is(true));

    }
    @Test // findAll
    public void findAll() {
        Tracker tracker = new Tracker();
        Item first = new Item("one");
        Item second = new Item("two");
        Item third = new Item("three");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        Item[] itemsTest = new Item[3];
        itemsTest[0] = first;
        itemsTest[1] = second;
        itemsTest[2] = third;
        assertThat(tracker.findAll(), is(itemsTest));
    }
    @Test // findByName
    public void findByName() {
        Tracker tracker = new Tracker();
        Item first = new Item("one");
        Item second = new Item("one");
        Item third = new Item("two");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        Item[] itemsTest = new Item[3];
        itemsTest[0] = first; // one
        itemsTest[1] = second; // one
        itemsTest[2] = null;
        assertThat(tracker.findByName("one"), is(itemsTest));
    }
    @Test // findById
    public void findById() {
        Tracker tracker = new Tracker();
        Item first = new Item("one");
        Item second = new Item("two");
        Item third = new Item("three");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        assertThat(tracker.findById(first.getId()), is(first));
    }

}