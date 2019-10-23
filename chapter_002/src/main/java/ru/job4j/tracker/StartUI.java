package ru.job4j.tracker;

import ru.job4j.tracker.actions.*;

/**
 * Главый класс с main методом
 * @author Daniils Loputevs
 * @version $Id$
 * @since 22.10.19
 **/

public class StartUI {

    public void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.length);
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] actions) {
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
                new CreateAction(),
                new ReplaceItem(),
                new DeleteItem(),
                new FindAllItemsAction(),
                new FindByNameItem(),
                new FindByIdItem()
        };
        new StartUI().init(validate, tracker, actions);
    }
}