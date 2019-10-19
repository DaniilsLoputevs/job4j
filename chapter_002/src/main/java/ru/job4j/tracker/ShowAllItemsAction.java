package ru.job4j.tracker;

public class ShowAllItemsAction implements UserAction {
    @Override
    public String name() {
        return "=== Show all Items ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
//        System.out.println("Список всех заявок");
        Item[] items =  tracker.findAll();
        for (Item item : items) {
            System.out.println(item.getName());
        }
        System.out.println();
        return true;
    }
}
