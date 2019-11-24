package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(
                this.out.toString(),
                is(
                        new StringBuilder()
                                .append("++++")
                                .append("+     +")
                                .append("+     +")
                                .append("++++")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test // Не работает!!!
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(
                this.out.toString(),
                is(
                        new StringBuilder()
                                .append("  ^  ")
                                .append(" ^ ^ ")
                                .append("^^^^^")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }


    @Test
    public void extra() {
        // получаем ссылку на стандартный вывод в консоль.
        // сохрвняем стандартный вывод
        PrintStream stdout = System.out;
        // Создаем буфур для хранения вывода.
        // Создаем переменную для теста. Тип данных, что мы будем проверять.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Заменяем стандартный вывод на вывод в пямять для тестирования.
        // подменяем стандартный вывод на тот, что мы будем сравнивать.
        System.setOut(new PrintStream(out));
        // выполняем действия пишушиее в консоль.
        new Paint().draw(new Square()); // У сетода должен быть return sout()
        // проверяем результат вычисления
        // вызываем ByteArray у, Уже заменённого вывода
        // пишем с чес мы сравниваем
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("++++")
                                .append("+     +")
                                .append("+     +")
                                .append("++++")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
        // возвращаем обратно стандартный вывод в консоль.
        System.setOut(stdout);
    }
}