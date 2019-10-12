package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test // add
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }
    @Test // replace
    public void whenReplaceNameThenReturnNewName() {
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
    @Test
    public void findAll() {
        Tracker tracker = new Tracker();
        Item first = new Item("first");
        Item second = new Item("second");
        Item third = new Item("third");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        Item[] itemsTest = new Item[3];
        itemsTest[0] = first;
        itemsTest[0] = second;
        itemsTest[0] = third;
        assertThat(tracker.findAll(), is(itemsTest));
    }
    @Test
    public void findByName() {
        Tracker tracker = new Tracker();
        Item first = new Item("first");
        Item second = new Item("first");
        Item third = new Item("second");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        Item[] itemsTest = new Item[3];
        itemsTest[0] = first;
        itemsTest[1] = second;
        itemsTest[2] = null;
        assertThat(tracker.findByName("first"), is(itemsTest));
    }
    @Test
    public void delete() {
        Tracker tracker = new Tracker();
        Item first = new Item("first");
        Item second = new Item("second");
        Item third = new Item("third");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        assertThat(tracker.delete(second.getId()), is(true));

    }
}