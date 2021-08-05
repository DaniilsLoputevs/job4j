package ru.job4j.waitnotify;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " - "
                    + Thread.currentThread().getState() + " - Count UP +1");
            count++;
            this.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count != total) {
                try {
                    System.out.println(Thread.currentThread().getName() + " - WAITED");
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        var counter = new CountBarrier(2);
        Thread master = new Thread(
                counter::count,
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    counter.await();
                    System.out.println("slave - AWAKE - do logic");
                },
                "Slave"
        );
        System.out.println("Program start");
        counter.count();

        System.out.println(master.getName() + " - STARTED");
        master.start();
        System.out.println(slave.getName() + " - STARTED");
        slave.start();

        try {
            System.out.println("main - sleep - 1000 ms");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main - AWAKE");

        System.out.println("main - join others");
        try {
            master.join();
            slave.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main - finish work");
        System.out.println("Program finish");
        /*
        Console LOG:
        Program start
        main - RUNNABLE - Count UP +1
        Master - STARTED
        Slave - STARTED
        main - sleep - 1000 ms
        Master - RUNNABLE - Count UP +1
        slave - AWAKE - do logic
        main - AWAKE
        main - join others
        main - finish work
        Program finish
         */
    }

}
