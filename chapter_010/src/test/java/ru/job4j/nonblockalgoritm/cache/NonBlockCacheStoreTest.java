package ru.job4j.nonblockalgoritm.cache;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.nonblockalgoritm.cache.model.Base;
import ru.job4j.nonblockalgoritm.cache.model.CacheTypeOne;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class NonBlockCacheStoreTest {
    private Base base;
    private final NonBlockCacheStore cacheStore = new NonBlockCacheStore(5);
    private final AtomicReference<Exception> ex = new AtomicReference<>();

    private final Thread thread1 = new Thread(() -> {
        try {
            base = new Base(1, 0, new CacheTypeOne("Minsk"));
            cacheStore.update(base);
        } catch (OptimisticException e) {
            ex.set(e);
        }
    });
    private final Thread thread2 = new Thread(() -> {
        try {
            base = new Base(1, 0, new CacheTypeOne("Moscow"));
            cacheStore.update(base);
        } catch (OptimisticException e) {
            ex.set(e);
        }
    });

    @Test
    public void whenVersionsEquals() throws InterruptedException {
        base = new Base(1, 0, new CacheTypeOne("Minsk"));
        cacheStore.add(base);

        thread1.start();

        thread1.join();

        assertEquals(0, base.getVersion());
        assertEquals("Minsk", base.getCacheModel().getContent());
    }

    @Test
    public void whenVersionsNotEquals() throws InterruptedException {
        base = new Base(1, 0, new CacheTypeOne("Minsk"));
        cacheStore.add(base);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertEquals("Fail update cache! It could be \"Wasted record\"!",
                ex.get().getMessage());
    }

    @Ignore
    public void example() throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new RuntimeException("Throw Exception in Thread");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        assertEquals("Throw Exception in Thread", ex.get().getMessage());
    }

}