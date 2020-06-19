package ru.job4j.exam2;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.exam2.io.Input;
import ru.job4j.exam2.io.Output;
import ru.job4j.exam2.io.StubInput;
import ru.job4j.exam2.players.StubPlayer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class GameTest {
    private Field field;
    private final List<Object> log = new ArrayList<>();
    private final Output logOut = new Output(log::add);
    private StubInput input;
    private List<Input> inputList;


    @Before
    public void setUp() throws Exception {
        this.field = new Field(5, '_');

        input = new StubInput(List.of(
                new StubPlayer("p1", 'O', logOut),
                new StubPlayer("p2", 'X', logOut)
        ));
        inputList = List.of(input);
    }



    @Test
    public void winTest() {
        this.input.setArray(new int[][]{
                // p1     p2      p1       p2
                {1, 1}, {1, 4}, {2, 2}, {1, 5},
                {3, 3}, {2, 4}, {4, 4}, {2, 5},
                {5, 5}
        });
        new Game().run(field, inputList);

        // O _ _ X X
        // _ O _ X X
        // _ _ O _ _
        // _ _ _ O _
        // _ _ _ _ O
        assertTrue(log.contains("Finish! The Winner is: p1" + System.lineSeparator()));
    }

    @Test
    public void drawTest() {
        this.input.setArray(new int[][]{
                // p1     p2      p1       p2
                {1, 1}, {1, 4}, {2, 2}, {1, 5},
                {3, 3}, {2, 4}, {4, 4}, {2, 5},
                {1, 2}, {3, 4}, {1, 3}, {5, 5},
                {5, 4}, {2, 3}, {2, 1}, {3, 5},
                {4, 5}, {3, 1}, {4, 1}, {4, 2},
                {5, 1}, {3, 2}, {5, 2}, {4, 3},
                {5, 3}
        });
        new Game().run(field, inputList);

        // O O O X X
        // O O X X X
        // X X O X X
        // O X X O O
        // O O O O X
        assertTrue(log.contains("Finish! It is a Draw!" + System.lineSeparator()));
    }
}