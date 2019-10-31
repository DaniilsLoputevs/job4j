package ru.job4j.tracker;

import ru.job4j.tracker.item_actions.*;

/**
 * Главый класс с main методом
 * @author Daniils Loputevs
 * @version $Id$
 * @since 24.10.19
 **/

public class StartUI {

    /**
     * Старт всей программы
     * @param input - ValidateInput(input)
     * @param tracker - Tracker tracker
     * @param actions - UserAction[] - массив всех действий с заявками
     */
    void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.length);
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    /**
     * показывает имя всех Действий добавленых в item_actions
     * @param actions - UserAction[] actions
     */
    void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.length; index++) {
            System.out.println(index + ". " + actions[index].name());
        }
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new ExitOfProgramm(),
                new Create(),
                new Replace(),
                new DeleteItem(),
                new FindAll(),
                new FindByName(),
                new FindById()
        };
        new StartUI().init(validate, tracker, actions);
    }
}