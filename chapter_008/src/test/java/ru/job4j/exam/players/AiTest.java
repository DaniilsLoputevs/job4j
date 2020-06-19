package ru.job4j.exam.players;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.exam.Field;
import ru.job4j.exam.io.Input;
import ru.job4j.exam.io.Output;
import ru.job4j.exam.io.StubInput;
import ru.job4j.exam.io.ValidateInput;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AiTest {
    private Field field = new Field(5, '_');
    private List<Object> stubOutput = new ArrayList<>();
    private Input playerInput;
    private Output aiOutput = new Output(stubOutput::add);
    private static final String LS = System.lineSeparator();

    @Before
    public void setUp() {
        var answers = new String[]{
                " fhdh", "asd sf", "1", "5 as", "2 3"
        };
        playerInput = new ValidateInput(new StubInput(answers));
    }

    @Test
    public void run() {
        var test = new Ai("ai Test", 'O', playerInput, aiOutput, field);
        var temp = new int[2];
        var resultField = new ArrayList<>();
        var expectedField = List.of(
                '_', '_', '_', '_', '_', LS,
                '_', '_', '_', '_', '_', LS,
                '_', '_', '_', '_', '_', LS,
                '_', '_', '_', '_', '_', LS,
                '_', '_', '_', 'O', 'O', LS
        );

        temp = test.makeMove();
        field.writeOnField(temp[0], temp[1], 'O');
        temp = test.makeMove();
        field.writeOnField(temp[0], temp[1], 'O');

        field.showField(new Output(resultField::add));
        assertEquals(23, field.getFreeCells());
        assertEquals(expectedField, resultField);
    }

}

