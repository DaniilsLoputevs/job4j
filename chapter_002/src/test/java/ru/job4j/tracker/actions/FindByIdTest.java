package ru.job4j.tracker.actions;

import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/** Вот такая должа быть строка у метода
 * System.out.println(String.format("%s %s", item.getId(), item.getName()));
 * System.out.println();
 */

public class FindByIdTest {

    @Test
    public void findAllClassTest() {
        // Подгатовка
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        PrintStream defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
        // Основной блок
        Tracker tracker = new Tracker();
        Item item = new Item("example");
        tracker.add(item);
        // Действие
        new FindById(1, "").execute(new StubInput(new String[] {item.getId()}), tracker);
        // Такая же, строка, что метод кинул в sout();
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(item.getId() + " " + item.getName() + "\r" + "\n").toString();
        assertThat(new String(newOutput.toByteArray()), is(expect));
        // Возвращаем стандартный вывод
        System.setOut(defaultOutput);
    }
}
