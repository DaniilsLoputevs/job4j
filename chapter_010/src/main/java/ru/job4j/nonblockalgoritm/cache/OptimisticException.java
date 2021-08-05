package ru.job4j.nonblockalgoritm.cache;

public class OptimisticException extends RuntimeException {
    public OptimisticException() {
        super("Fail update cache! It could be \"Wasted record\"!");
    }
}