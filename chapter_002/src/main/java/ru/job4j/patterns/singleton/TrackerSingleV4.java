package ru.job4j.patterns.singleton;

import ru.job4j.tracker.Item;

public class TrackerSingleV4 {
    private TrackerSingleV4() {
    }

    public static TrackerSingleV4 getInstance() {
        return Holder.INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    private static final class Holder {
        private static final TrackerSingleV4 INSTANCE = new TrackerSingleV4();
    }
}