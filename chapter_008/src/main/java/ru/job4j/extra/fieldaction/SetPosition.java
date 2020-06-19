package ru.job4j.extra.fieldaction;

/**
 * Set player position.
 * Switch player to position
 */
public class SetPosition implements FieldAction {
    String info;
    int actionParam;

    public SetPosition(String info, int actionParam) {
        this.info = info;
        this.actionParam = actionParam;
    }

    @Override
    public String info() {
        return this.info;
    }

    @Override
    public int execute(int playerPosition) {
        return actionParam;
    }
}