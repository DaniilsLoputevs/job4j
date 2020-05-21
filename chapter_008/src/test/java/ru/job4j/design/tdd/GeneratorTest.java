package ru.job4j.design.tdd;

import org.junit.Test;

import java.util.Map;

public class GeneratorTest {

    @Test
    public void produceTest() {
        var template = "I am a ${name}, Who are ${subject}? ";
        var args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        );
        // stub
        Generator generator = new StubGenerator();

        var result = generator.produce(template, args);
        var expected = "I am a Petr Arsentev, Who are you? ";

    }

    /**
     * Stub while Generation doesn't have implementation.
     */
    class StubGenerator implements Generator {
        @Override
        public String produce(String template, Map<String, String> args) {
            return "I am a Petr Arsentev, Who are you? ";
        }
    }
}