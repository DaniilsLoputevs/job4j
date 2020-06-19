package ru.job4j.loop;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {
    private String sls = System.lineSeparator();

    @Test // board String 3x3
    public void board3x3String() {
        var temp = new StringBuilder();
        temp.append("X X").append(sls);
        temp.append(" X ").append(sls);
        temp.append("X X").append(sls);
        var result = Board.paintString(3, 3);
        assertEquals(temp.toString(), result);
    }

    @Test // board String 5x5
    public void board5x5String() {
        var temp = new StringBuilder();
        temp.append("X X X").append(sls);
        temp.append(" X X ").append(sls);
        temp.append("X X X").append(sls);
        temp.append(" X X ").append(sls);
        temp.append("X X X").append(sls);
        var result = Board.paintString(5, 5);
        assertEquals(temp.toString(), result);
    }

    @Test // board String 7x7
    public void board7x7String() {
        var temp = new StringBuilder();
        temp.append("X X X X").append(sls);
        temp.append(" X X X ").append(sls);
        temp.append("X X X X").append(sls);
        temp.append(" X X X ").append(sls);
        temp.append("X X X X").append(sls);
        temp.append(" X X X ").append(sls);
        temp.append("X X X X").append(sls);
        var result = Board.paintString(7, 7);
        assertEquals(temp.toString(), result);
    }

    @Ignore // board String 3x3
    public void board3x3() {
        Board.paint(3, 3);
    }

    @Ignore // board String 5x5
    public void board5x5() {
        Board.paint(5, 5);
    }

    @Ignore // board String 7x7
    public void board7x7() {
        Board.paint(7, 7);
    }
}
