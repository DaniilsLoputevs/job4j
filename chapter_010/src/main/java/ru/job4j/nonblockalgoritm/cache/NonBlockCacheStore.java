package ru.job4j.nonblockalgoritm.cache;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.nonblockalgoritm.cache.model.Base;

import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class NonBlockCacheStore implements CacheStore {
    private final ConcurrentHashMap<Integer, Base> store;

    public NonBlockCacheStore(int initSize) {
        this.store = new ConcurrentHashMap<>(initSize);
    }

    @Override
    public void add(Base model) {
        if (store.containsKey(model.getId())) {
            update(model);
        } else {
            store.put(model.getId(), model);
        }
    }

    @Override
    public Base get(int id) {
//        var temp = store.get(id);
//        var rsl = new Base(temp.getId(), temp.getVersion() +1);

        return null;
    }

    @Override
    public void update(Base model) {
        store.computeIfPresent(model.getId(), (key, value) -> {
            if (model.getVersion() != value.getVersion()) {
                throw new OptimisticException();
            }

            var rsl = copyBase(model);
            store.put(key, rsl);
            return rsl;
        });
    }

    @Override
    public Base delete(Base model) {
        return store.remove(model);
    }

    private static Base copyBase(Base base) {
        return new Base(base.getId(), base.getVersion() + 1, base.getCacheModel());
    }
}
