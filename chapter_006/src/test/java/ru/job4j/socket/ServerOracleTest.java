package ru.job4j.socket;


import com.google.common.base.Joiner;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerOracleTest {
    private static final String LN = System.getProperty("line.separator");


    @Test
    public void exit() throws IOException {
        this.testServerOracle("exit", "");
    }
    @Test
    public void hello() throws IOException {
        this.testServerOracle(
                Joiner.on(LN).join(
                        "hello",
                        "exit"
                ),
                String.format("Hello, dear friend, I'm a oracle.%s%s", LN, LN)
        );
    }
    @Test
    public void another() throws IOException {
        this.testServerOracle(
                Joiner.on(LN).join(
                        "unsupported ask",
                        "exit"
                ),
                String.format("I don't understood.%s%s", LN, LN)
        );
    }


    /** Основна тестов задачи.
     *
     * Как работает mock:
     * mock(Socket.class) - создаёт объект с Пустыми(не реальзоваными) методами.
     * when(socket.getInputStream()).thenReturn(in); - назначаем return конкретного метода.
     * Далее, передаем объект в метод, а в методе, используютя Mock-методы.(пустые, но мы переназначили их return)
     * это позволяет нам использовать методы объекта НЕ СОЗДОВАЯ сам экземпляр, через КОНСТРУКТОР
     * + НЕ МЕНЯЯ код внутри метода, под тест.
     *
     *
     * @param input - Входные парам.
     * @param expected - ожмдаемые парам.
     * @throws IOException - если всё плохо.
     */
    private void testServerOracle(String input, String expected) throws IOException {
        var socket = mock(Socket.class);
        var in = new ByteArrayInputStream(input.getBytes());
        var out = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        var oracle = new ServerOracle(socket);

        oracle.start();
        assertThat(out.toString(), is(expected));
    }


    /** Конспект, как работает Joiner из Guava
     * позволяет правильно(для ОС) разделять строки.
     *
     * private static final String LN = System.getProperty("line.separator");
     *  Joiner.on(LN).join( stringOne, StringTwo) - Возвращает массив строк
     *  с привильным делением на строки.
     */
    @Ignore
    public void testJoiner() {
        String string = Joiner.on(LN).join(
                "Hello",
                "exit"
        );
        ByteArrayInputStream in = new ByteArrayInputStream(
                Joiner.on(LN).join(
                        "Hello",
                        "exit"
                ).getBytes()
        );
    }
}