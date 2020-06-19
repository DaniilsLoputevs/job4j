package ru.job4j.exam2;

import ru.job4j.exam2.io.ConsoleInput;
import ru.job4j.exam2.io.Input;
import ru.job4j.exam2.io.Output;
import ru.job4j.exam2.players.Ai;
import ru.job4j.exam2.players.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Field field = new Field(5, '_');
        var botOutputLog = new ArrayList<>();
        Input consoleIn = new ConsoleInput(List.of(
//                new Ai("bot", 'X', new Output(botOutputLog::add)),
//                new User("test", 'O', new Output(System.out::print))
                new User("test", 'O', new Output(System.out::print)),
                new Ai("bot", 'X', new Output(botOutputLog::add))
        ));
        List<Input> inputList = List.of(consoleIn);

        new Game().run(field, inputList);
    }
}
