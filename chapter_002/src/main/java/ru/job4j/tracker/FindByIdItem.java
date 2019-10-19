package ru.job4j.tracker;

public class FindByIdItem implements UserAction {
    @Override
    public String name() {
        return "=== Find Item by Id ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.print("Enter id: ");
        String id =  input.askStr("");
        tracker.findById(id);
        System.out.println();
        return true;
    }
}
