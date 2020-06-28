package daniils.api;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class ArgsLambdaExamples {
    @Test
    public void runFlag() {
        var args = new String[]{"-exe", "something"};

        Properties prop = new ArgsLambda.Builder()
                .load(args)
                .add("-exe")
                .run();

        assertEquals("-exe", prop.getProperty("-exe"));
    }

    @Test
    public void runNormalKey() {
        var args = new String[]{"url", "w.leningrad.ru"};
        var log = new ArrayList<String>();

        Properties prop = new ArgsLambda.Builder()
                .load(args)
                .add("url", arg -> arg.contains(".org"))
                .print(log::add)
                .run();

        var expected = "WARNING - ArgsLambda: This key and value fail validate:"
                + System.lineSeparator()
                + "key:   " + "url" + System.lineSeparator()
                + "value: " + "w.leningrad.ru";

        assertEquals(expected, log.get(0));
    }

    @Test
    public void runMultiKey() {
        var args = new String[]{"-exe", "1", "kb", "s"};

        Map<String, String> prop = new ArgsLambda.Builder()
                .add("-exe", List.of(
                        arg -> arg.equals("1"),
                        arg -> arg.equals("kb"),
                        arg -> arg.equals("s")))
                .load(args)
                .runToMap();

        var values = prop.get("-exe").split("-");
        assertEquals("1", values[0]);
        assertEquals("kb", values[1]);
        assertEquals("s", values[2]);
    }

    @Test
    public void runOverloadKeyParams() {
        var args = new String[]{"-exe", "10", "mb"};

        Properties prop = new ArgsLambda.Builder()
                .add("-exe", List.of(
                        arg -> arg.equals("1"),
                        arg -> arg.equals("kb"),
                        arg -> arg.equals("s")))
                .continuable()
                .loadAndRun(args);

        Properties propOverload = new ArgsLambda.Builder()
                .add("-exe", List.of(
                        arg -> arg.equals("10"),
                        arg -> arg.equals("mb")))
                .loadAndRun(args);

        new ArgsLambda.Builder()
                .merge(prop, propOverload);

        var values = prop.getProperty("-exe").split("-");
        assertEquals("10", values[0]);
        assertEquals("mb", values[1]);
    }

}