package ru.job4j.tracker;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        tracker.add(example); // при добавлении в tracker, у заявки появляеться id
        // достаём из массива заявку по id первой
        Item test = tracker.findById(example.getId());
        // сравниваем, что привер и вторая заявки одинаковы
        assertThat(example, is(test));
    }

    /**
     * replace()
     */
    @Test
    public void replace() {
        Tracker tracker = new Tracker();
        Item example = new Item("example");
        // Добавляем заявку в трекер. Теперь у неё есть свой id (!= null)
        tracker.add(example);
        Item test = new Item("test"); // Создаем новую заявку.
        // Обновляем заявку в трекере.
        tracker.replace(example.getId(), test);
        // Проверяем, что заявка с таким id имеет новое имя "test"
        assertThat(tracker.findById(example.getId()).getName(), is("test"));
    }

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

        tracker.delete(second.getId());

        System.out.println(first);
        System.out.println(second);
        System.out.println(third);

        Item[] example = new Item[] {
                first, third
        };

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
        Item[] itemsTest = new Item[] {
                example, test
        };
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
        Item[] itemsTest = new Item[] {
                first, third // "example"
        };
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
    public void isIdExist() {
        // Подгатовка
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        PrintStream defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
        // Основной блок
        Tracker tracker = new Tracker();
        Item example = new Item("example");
        tracker.add(example);
        tracker.findById(tracker.generateId());
        // Сравнение
        MatcherAssert.assertThat(
                newOutput.toString(),
                is(String.format("Такого id не существует.%n"))
        );
        // Возвращаем стандартный вывод
        System.setOut(defaultOutput);
    }

}