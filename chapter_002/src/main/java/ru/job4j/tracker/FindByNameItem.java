package ru.job4j.tracker;

public class FindByNameItem implements UserAction {
    @Override
    public String name() {
        return "=== Find Item by Name ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.print("Enter name: ");
        String name =  input.askStr("");
        tracker.findByName(name);
        System.out.println();
        return true;
    }
}
