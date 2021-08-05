package ru.job4j;

/**
 * Lazy Singleton.
 */
public class Cache {
    private static Cache cache;

    public synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    public static void main(String[] args) {
        final Cache[] cacheOne = new Cache[1];
        final Cache[] cacheTwo = new Cache[1];

        var threadOne = new Thread(
                () -> {
                    cacheOne[0] = Cache.instOf();
                }
        );
        var threadTwo = new Thread(
                () -> {
                    cacheTwo[0] = Cache.instOf();
                }
                );

        threadOne.start();
        threadTwo.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cacheOne[0]);
        System.out.println(cacheTwo[0]);
        System.out.println(cacheOne[0].equals(cacheTwo[0]));
    }
}
