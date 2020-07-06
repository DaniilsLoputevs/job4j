package ru.job4j.nonblockalgoritm.cache;

public interface CacheModel<T> {
    T getContent();

    void setContent(T content);
}
