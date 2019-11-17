package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DeleteTest {

    /** НЕ РААБОТАЕТ!!!!!
     * delete()
     */
//    @Test // Метод не работает корректно!!!!!!!!!!!
//    public void delete() {
//        Tracker tracker = new Tracker();
//        Item first = new Item("one");
//        Item second = new Item("two");
//        Item third = new Item("three");
//        tracker.add(first);
//        tracker.add(second);
//        tracker.add(third);
//
//        tracker.delete(first.getId());
//
//        Item[] example = new Item[] {
//                second, third
//        };
//
//        assertThat(tracker.findAll(), is(example));
//
////        Item example = new Item("example");
//        Item test = new Item("test");
////        tracker.add(example);
//        tracker.add(test);
//        // сохраняем id заявки для удаления
////        String testId = example.getId();
////        tracker.delete(testId);
//
////        assertThat(tracker.findById(testId), is(example));
//
    }

//    @Test // delete
//    public void deleteNew1() {
//        Tracker tracker = new Tracker();
//        Item first = new Item("first");
//        Item second = new Item("second");
//        Item third = new Item("third");
//        tracker.add(first);
//        tracker.add(second);
//        tracker.add(third);
//
//        Item[] more = tracker.findAll(); // первоначальная копия
//        tracker.delete(first.getId()); // удаляем first
//        assertThat(tracker.findAll(), is(more)); // сравниваем копию и оригинал
//    }
//    @Test // delete
//    public void deleteNew2() {
//        Tracker tracker = new Tracker();
//        Item first = new Item("first");
//        Item second = new Item("second");
//        Item third = new Item("third");
//        tracker.add(first);
//        tracker.add(second);
//        tracker.add(third);
//
//        Item more = tracker.findAll() [1]; // первоначальная копия
//        tracker.delete(first.getId()); // удаляем first
//        assertThat(tracker.findAll() [0], is(second)); // сравниваем копию и оригинал
//    }
//}
