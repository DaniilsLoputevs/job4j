package ru.job4j.loop;

import org.junit.Test;

public class BoardTest {
    @Test // board String 3x3
    public void board3x3String() {
        Board.paintString(3, 3);
    }
    @Test // board String 5x5
    public void board5x5String() {
        Board.paintString(5, 5);
    }
    @Test // board String 7x7
    public void board7x7String() {
        Board.paintString(7, 7);
    }

    @Test // board String 3x3
    public void board3x3() {
        Board.paint(3, 3);
    }
    @Test // board String 5x5
    public void board5x5() {
        Board.paint(5, 5);
    }
    @Test // board String 7x7
    public void board7x7() {
        Board.paint(7, 7);
    }
}
