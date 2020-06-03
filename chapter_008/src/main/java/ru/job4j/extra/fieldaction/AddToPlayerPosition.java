package ru.job4j.extra.fieldaction;

/**
 * Add value to current player's position.
 */
public class AddToPlayerPosition extends BaseAction {
    public AddToPlayerPosition(String info, int actionParam) {
        super(info, actionParam);
    }

    @Override
    public String info() {
        return super.info;
    }

    @Override
    public int execute(int playerPosition) {
        return actionParam + actionParam;
    }
}