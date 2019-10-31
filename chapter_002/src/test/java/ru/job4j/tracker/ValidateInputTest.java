package ru.job4j.tracker;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {

    @Test // Please select key from menu.
    public void first() {
        // Подгатовка
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        PrintStream defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"9", "6"})
        );
        input.askInt("9", 6); // Действие


        // Такая же, строка, что метод кинул в sout();
//        String expect = "Please select key from menu.%n";

//        Assert.assertThat(new String(newOutput.toByteArray()), Is.is(expect));





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
                new StubInput(new String[] {"invalid", "1"})
        );
        input.askInt("Enter", 1);
        // Сравнение
        assertThat(
                newOutput.toString(),
                is(String.format("Please enter validate data again.%n"))
        );
        // Возвращаем стандартный вывод
        System.setOut(defaultOutput);
    }
}