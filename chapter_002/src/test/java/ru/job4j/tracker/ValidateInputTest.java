package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {

    @Test // Please select key from menu.
    public void first() {
        // Подгатовка
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        PrintStream defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
        // Основной блок
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"9", "1", "2"})
        );
        input.askInt("take first answer from arr", 6); // Действие
        assertThat(
                newOutput.toString(),
                is(String.format("Please select key from menu.%n"))
        );
        // Возвращаем стандартный вывод
        System.setOut(defaultOutput);
    }

    @Test // Please enter validate data again.
    public void second() {
        // Подгатовка
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        PrintStream defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
        // Основной блок
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"invalid", "1", "2"})
        );
        input.askInt("take first answer from arr", 4);
        // Сравнение
        assertThat(
                newOutput.toString(),
                is(String.format("Please enter validate data again.%n"))
        );
        // Возвращаем стандартный вывод
        System.setOut(defaultOutput);
    }
}