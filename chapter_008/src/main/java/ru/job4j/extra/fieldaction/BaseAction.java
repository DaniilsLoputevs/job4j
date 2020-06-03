package ru.job4j.extra.fieldaction;

/**
 * abstract base for implementation of {@code FieldAction}.
 */
public abstract class BaseAction implements FieldAction {
    String info;
    int actionParam;

    public BaseAction(String info, int actionParam) {
        this.info = info;
        this.actionParam = actionParam;
    }
}
