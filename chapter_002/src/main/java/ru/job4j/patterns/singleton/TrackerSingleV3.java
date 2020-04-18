package ru.job4j.patterns.singleton;

public class TrackerSingleV3 {
    private static final TrackerSingleV3 INSTANCE = new TrackerSingleV3();

    private TrackerSingleV3() {
    }

    public static TrackerSingleV3 getInstance() {
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }
}

