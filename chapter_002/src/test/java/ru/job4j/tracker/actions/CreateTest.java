package ru.job4j.tracker.actions;

import org.junit.Test;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CreateTest {

    @Test
    public void createActionClassTest() {
        // Подгатовка
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        PrintStream defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
        // Основной блок
        Tracker tracker = new Tracker();
        String nameCreateItem = "test";
        // Действие
        new Create(1, "").execute(new StubInput(new String[] {nameCreateItem}), tracker);
        assertThat(tracker.findAll(), is(tracker.findByName("test")));
        // Возвращаем стандартный вывод
        System.setOut(defaultOutput);
    }
}