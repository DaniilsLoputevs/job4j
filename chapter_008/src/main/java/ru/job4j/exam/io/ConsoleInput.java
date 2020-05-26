package ru.job4j.exam.io;

import java.util.Scanner;

/**
 * Input from console.
 */
public class ConsoleInput extends BaseInput {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int[] askTurnParam(String enterParam, Output output) throws NumberFormatException {
        output.accept(enterParam);
        var temp = scanner.nextLine();
        return convertAnswer(temp);

    }

}
