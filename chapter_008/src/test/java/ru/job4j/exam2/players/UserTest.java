package ru.job4j.exam2.players;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.exam2.io.Output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private ByteArrayOutputStream newOutput;
    private PrintStream defaultOutput;

    private Output playerOutput;


    @Before
    public void changeOutput() {
        newOutput = new ByteArrayOutputStream();
        defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
        // init part
        playerOutput = new Output(System.out::print);
    }

    @After
    public void returnOutput() {
        System.setOut(defaultOutput);
    }

    @Test
    public void testValidate() throws IOException {
        var answers = new String[]{
                " fhdh", "asd sf", "1", "5 as", "2 3"
        };
        var test = new User("test", 'O', playerOutput);
        test.parseAnswer(answers[0]);

        assertEquals("Wrong turn params!" + System.lineSeparator(), newOutput.toString());
    }

}