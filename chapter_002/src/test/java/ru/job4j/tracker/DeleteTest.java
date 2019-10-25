package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DeleteTest {

    /** НЕ РААБОТАЕТ!!!!!
     * delete()
     */
    @Test // Метод не рпботает корректно!!!!!!!!!!!
    public void delete() {
        Tracker tracker = new Tracker();
        Item first = new Item("one");
        Item second = new Item("two");
        Item third = new Item("three");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        Item[] example = new Item[] {
                first, second, third
        };
        tracker.delete(first.getId());

        assertThat(tracker.findAll(), is(example));

//        Item example = new Item("example");
        Item test = new Item("test");
//        tracker.add(example);
        tracker.add(test);
        // сохраняем id заявки для удаления
//        String testId = example.getId();
//        tracker.delete(testId);

//        assertThat(tracker.findById(testId), is(example));

    }

    @Test // delete
    public void delete_old() {
        Tracker tracker = new Tracker();
        Item first = new Item("first");
        Item second = new Item("second");
        Item third = new Item("third");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);

        Item[] more = tracker.findAll(); // первоначальная копия
        tracker.delete(first.getId()); // удаляем first
        assertThat(tracker.findAll(), is(more)); // сравниваем копию и оригинал

    }
}
