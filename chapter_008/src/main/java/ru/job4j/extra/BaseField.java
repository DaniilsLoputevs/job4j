package ru.job4j.extra;

import ru.job4j.extra.fieldaction.FieldAction;

import java.util.HashMap;

/**
 * Abstract base for implementation of {@code Field}.
 */
public abstract class BaseField implements Field {
    protected HashMap<Integer, FieldAction> actionMap;

    public BaseField(int size) {
        this.actionMap = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            this.actionMap.put(i, null);
        }
    }

    public BaseField(HashMap<Integer, FieldAction> actionMap) {
        this.actionMap = actionMap;
    }
}
