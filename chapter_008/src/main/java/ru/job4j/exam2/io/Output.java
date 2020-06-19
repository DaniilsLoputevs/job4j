package ru.job4j.exam2.io;

import java.util.function.Consumer;

/**
 * Usual output like method's link.
 */
public class Output {
    private Consumer<Object> output;

    public Output(Consumer<Object> output) {
        this.output = output;
    }

    public void accept(Object string) {
        output.accept(string);
    }
}
