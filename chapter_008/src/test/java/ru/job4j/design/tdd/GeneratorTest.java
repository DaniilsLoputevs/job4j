package ru.job4j.design.tdd;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GeneratorTest {
    private ByteArrayOutputStream newOutput;
    private PrintStream defaultOutput;

    @Before
    public void changeOutput() {
        newOutput = new ByteArrayOutputStream();
        defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
    }

    @After
    public void returnOutput() {
        System.setOut(defaultOutput);
    }

    @Test
    public void normalTest() {
        var template = "I am a ${name}, Who are ${subject}? ";
        var args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        );
        // stub
        Generator generator = new StubGenerator();

        String result = null;
        try {
            result = generator.produce(template, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        var expected = "I am a Petr Arsentev, Who are you? ";
        assertEquals(expected, result);
    }

    @Test
    public void moreArgsTests() {
        var template = "I am a ${name}, Who are ${subject}? ";
        var args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you",
                "oneMore", "exception"
        );
        // stub
        Generator generator = new StubGeneratorMoreArgs();

        String result;
        try {
            result = generator.produce(template, args);
        } catch (IllegalStateException e) {
            result = e.getMessage();
        }

        assertEquals("Map has unusable keys.", result);
    }

    @Test
    public void noNeededArgsTests() {
        var template = "I am a ${name}, Who are ${subject}? ";
        var args = Map.of(
                "name", "Petr Arsentev",
                "oneMore", "exception"
        );
        // stub
        Generator generator = new StubGeneratorNoNeededArgs();

        String result;
        try {
            result = generator.produce(template, args);
        } catch (IllegalStateException e) {
            result = e.getMessage();
        }

        assertEquals("Map doesn't contains need keys.", result);
    }

    class StubGenerator implements Generator {
        @Override
        public String produce(String template, Map<String, String> args) {
            return "I am a Petr Arsentev, Who are you? ";
        }
    }

    class StubGeneratorMoreArgs implements Generator {
        @Override
        public String produce(String template, Map<String, String> args) throws IllegalStateException {
            throw new IllegalStateException("Map has unusable keys.");
        }
    }

    class StubGeneratorNoNeededArgs implements Generator {
        @Override
        public String produce(String template, Map<String, String> args) throws IllegalStateException {
            throw new IllegalStateException("Map doesn't contains need keys.");
        }
    }
}