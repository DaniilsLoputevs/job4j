package ru.job4j.nonblockalgoritm.cache.model;

import net.jcip.annotations.Immutable;
import ru.job4j.nonblockalgoritm.cache.CacheModel;

import java.util.Objects;

@Immutable
public class Base {
    private final int id;
    private final int version;
    private final CacheModel cacheModel;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
        this.cacheModel = null;
    }

    public Base(int id, int version, CacheModel content) {
        this.id = id;
        this.version = version;
        this.cacheModel = content;
    }


    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public CacheModel getCacheModel() {
        return cacheModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id && version == base.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }
}