package ru.job4j.exam.io;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.exam.players.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InputTest {
    private List<Object> exceptionLog = new ArrayList<>();
    private Input playerInput;
    private Output playerOutput = new Output(exceptionLog::add);
    private static final String LS = System.lineSeparator();

    @Before
    public void setUp() {
        var answers = new String[]{
                " fhdh", "asd sf", "1", "5 as", "2 3"
        };
        playerInput = new ValidateInput(new StubInput(answers));
    }

    @Test
    public void inputAndValidateTest() {
        var player = new User("test", 'X', playerInput, playerOutput);
        var result = player.makeMove();

        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        assertEquals(exceptionLog.get(0), "Please enter numbers. Please enter date again:" + LS);
        assertEquals(exceptionLog.get(1), "Please enter numbers. Please enter date again:" + LS);
        assertEquals(exceptionLog.get(2), "Less that 2 arguments. Please enter date again:" + LS);
        assertEquals(exceptionLog.get(3), "Please enter numbers. Please enter date again:" + LS);
    }
}