package ru.job4j.extra.fieldaction;

/**
 * Set player position.
 * Switch player to position
 */
public class SetPosition extends BaseAction {
    public SetPosition(String info, int actionParam) {
        super(info, actionParam);
    }

    @Override
    public String info() {
        return super.info;
    }

    @Override
    public int execute(int playerPosition) {
        return actionParam;
    }
}