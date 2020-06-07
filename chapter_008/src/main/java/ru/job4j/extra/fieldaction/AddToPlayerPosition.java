package ru.job4j.extra.fieldaction;

/**
 * Add value to current player's position.
 */
public class AddToPlayerPosition  implements FieldAction{
    String info;
    int actionParam;

    public AddToPlayerPosition(String info, int actionParam) {
        this.info = info;
        this.actionParam = actionParam;
    }

    @Override
    public String info() {
        return this.info;
    }

    @Override
    public int execute(int playerPosition) {
        return actionParam + actionParam;
    }
}