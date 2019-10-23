package ru.job4j.tracker.singleton;

public class LoadOrder {

    private static String staticField = echo("static field");

    private static final String STATIC_FINAL_FIELD = echo("static final field");

    {
        echo("not-static block");
    }

    static {
        echo("static block");
    }

    private String surname = echo("field");

    private final String name = echo("final field");

    public LoadOrder(String msg) {
        echo("constructor " + msg);
    }

    public static String echo(String text) {
        System.out.println(text);
        return text;
    }


    private static final class Holder {
        private static final LoadOrder INSTANCE = new LoadOrder("static inner field");
    }


    public static void main(String[] args) {
//        LoadOrder order = new LoadOrder("main");
        TrackerSingle trackerSingle = TrackerSingle.INSTANCE; // V1 - 1. enum.
        TrackerSingleV2 trackerSingleV2 = TrackerSingleV2.getInstance(); // V2 - static field. Lazy loading.
        TrackerSingleV3 trackerSingleV3 = TrackerSingleV3.getInstance(); // V3 - static final field. Eager loading.
        TrackerSingleV4 trackerSingleV4 = TrackerSingleV4.getInstance(); // V4 - private static final class. Lazy loading.

    }


}