package ru.job4j.patterns.singleton;

public class TrackerSingleV2 {
    private static TrackerSingleV2 instance;

    private TrackerSingleV2() {
    }

    public static TrackerSingleV2 getInstance() {
        if (instance == null) {
            instance = new TrackerSingleV2();
        }
        return instance;
    }

    public Item add(Item model) {
        return model;
    }
}
