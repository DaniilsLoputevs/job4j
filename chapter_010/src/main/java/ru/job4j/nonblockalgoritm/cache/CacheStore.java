package ru.job4j.nonblockalgoritm.cache;

import ru.job4j.nonblockalgoritm.cache.model.Base;

public interface CacheStore {
    void add(Base model);

    Base get(int id);

    void update(Base model);

    Base delete(Base model);

    /**
     * Create new instance with increment version and copy all others values.
     * @param oldInstance -
     * @return -
     */
    private static Base copyValues(Base oldInstance) {
        return null;
    }
}
