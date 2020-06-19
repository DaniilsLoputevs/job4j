package ru.job4j.exam2.players;

import ru.job4j.exam2.Field;
import ru.job4j.exam2.GameTurn;
import ru.job4j.exam2.io.Output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class User implements Player {
    protected String name;
    protected char sign;
    protected Output output;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public User(String name, char sign, Output output) {
        this.name = name;
        this.sign = sign;
        this.output = output;
    }

    /* ---------------- Implements Methods -------------- */

    @Override
    public void acceptField(Field copyOfGameField) {
        showFieldToUser(copyOfGameField.getField());
    }

    @Override
    public GameTurn makeMve() {
        return new GameTurn(this.name, this.sign, makeTurnParams());
    }

    @Override
    public void acceptMessage(String message) {
        output.accept(message);
    }

    /* ---------------- Private Methods -------------- */

    private int[] makeTurnParams() {
        int[] rsl = null;
        while (rsl == null) {
            try {
                rsl = parseAnswer(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rsl;
    }

    /**
     * Convert Player turn params to normal format.
     * <p>
     * String -> int[].
     * <p>
     * * Convert *coordinates of cell* to *indexes of arrays*.
     *
     * @param string Player turn params for convert to normal format.
     * @return turn param in normal format.
     */
    public int[] parseAnswer(String string) {

        int[] result = null;
        if (string.matches("^.d* d*.$")) {
            var arr = string.split(" ");
            result = new int[2];
            result[0] = Integer.parseInt(arr[0]) - 1;
            result[1] = Integer.parseInt(arr[1]) - 1;
        } else {
            this.output.accept("Wrong turn params!" + System.lineSeparator());
        }
        return result;
    }


    private void showFieldToUser(char[][] field) {
        for (char[] aField : field) {
            for (char anAField : aField) {
                this.output.accept(anAField);
            }
            this.output.accept(System.lineSeparator());
        }
    }

}
