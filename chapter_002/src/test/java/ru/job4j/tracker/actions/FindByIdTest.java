package ru.job4j.tracker.actions;

import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.TrackerLocal;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindByIdTest {

    @Test
    public void findByIdClassTest() {
        // Подгатовка
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        PrintStream defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
        Consumer<String> output = System.out::println;
        // Основной блок
        TrackerLocal trackerLocal = new TrackerLocal();
        Item item = new Item("example");
        trackerLocal.add(item);
        // Действие
        new FindById(1, "").execute(new StubInput(new String[] {item.getId()}), trackerLocal, output);
        // Такая же, строка, что метод кинул в sout();
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(item.getId() + " " + item.getName()).toString();
        assertThat(new String(newOutput.toByteArray()), is(expect));
        // Возвращаем стандартный вывод
        System.setOut(defaultOutput);
    }
}