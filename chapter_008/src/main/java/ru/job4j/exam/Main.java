package ru.job4j.exam;

import ru.job4j.exam.io.ConsoleInput;
import ru.job4j.exam.io.Output;
import ru.job4j.exam.io.ValidateInput;
import ru.job4j.exam.players.Ai;
import ru.job4j.exam.players.BasePlayer;
import ru.job4j.exam.players.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Some Terms for JavaDoc:
 * Player - mean any player in game.
 * turn params - mean coordinate on game field.
 * Game field - mean main game field like char[][].
 */
public class Main {
    public static void main(String[] args) {
        Field field = new Field(5, '_');
        var botOutputLog = new ArrayList<>();

        List<BasePlayer> players = List.of(
                new User("Sergey - Myton - Offer", 'O',
                        new ValidateInput(new ConsoleInput()),
                        new Output(System.out::print)),
                new Ai("Bot - 4urka!", 'X',
                        new ValidateInput(new ConsoleInput()),
                        new Output(botOutputLog::add), field)
        );

        new Game(field, players).start();
    }

}
