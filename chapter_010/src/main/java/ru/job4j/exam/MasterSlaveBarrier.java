package ru.job4j.exam;

import java.util.function.Predicate;

public class MasterSlaveBarrier {
    private final Thread master;
    private final Thread slave;
    private final Object lock = new Object();

    public MasterSlaveBarrier(Thread master, Thread slave) {
        this.master = master;
        this.slave = slave;
    }

    public void tryMaster() {
        this.master.interrupt();
    }

    public void trySlave() {

    }

    public void doneMaster() {

    }

    public void doneSlave() {

    }

    public void runWhile(Predicate<Boolean> predicate) {
        while (predicate.test(true)) {
            tryMaster();
            doneMaster();
            trySlave();
            doneSlave();
        }
    }





    /* OLD Version */


//    private final Thread master;
//    private final Thread slave;
//    private final AtomicBoolean start = new AtomicBoolean(true);
//
//    public MasterSlaveBarrier(Thread master, Thread slave) {
//        if (master != null && slave != null) {
//            throw new IllegalStateException("threads a re NULL");
//        }
//        this.master = master;
//        this.slave = slave;
//    }

//    private final AtomicBoolean masterExe = new AtomicBoolean(true);
//
//    public void tryMaster() {
//        try {
//            masterExe.set(false);
//            masterExe.notify();
//            masterExe.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void trySlave() {
//        try {
//            masterExe.set(true);
//            masterExe.notify();
//            masterExe.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


    //    public void run() {
////        master.start();
////        slave.start();
//        while (start.get()) {
//            tryMaster();
//            doneMaster();
//            trySlave();
//            doneSlave();
//        }
//    }
//
//    public void tryMaster() {
//        if (master.getState() != Thread.State.NEW) {
//            master.notify();
//        } else {
//            master.start();
//        }
//    }
//
//    public void trySlave() {
//
//    }
//
//    public void doneMaster() {
//
//    }
//
//    public void doneSlave() {
//
//    }


//    public static void main(String[] args) {
//        Thread thread = new Thread(
//                () -> {
//                    for (int i = 0; i < 100000; i++) {
//                        System.out.println(i);
//                        if (Thread.currentThread().isInterrupted()) {
//                            break;
//                        }
//                    }
//
//                });
//        thread.start();
//        thread.interrupt();
//    }

//    public static void main(String[] args) {
//
//        var regulator = new MasterSlaveBarrier();
////        var switcher = new MasterSlaveBarrier(
//                var t1 = new Thread(() -> {
//                    while (true) {
//                        System.out.println("Master A");
//                        try {
//                            Thread.sleep(1000);
//
//                            regulator.masterExe.wait();
//                            regulator.masterExe.notify();
////                            regulator.trySlave();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, "master");
//                var t2 = new Thread(() -> {
//                    while (true) {
//                        System.out.println("Slave B");
//                        try {
//                            Thread.sleep(1000);
//
//                            regulator.masterExe.wait();
//                            regulator.masterExe.notify();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
////                        regulator.tryMaster();
//                    }
//                }, "slave");
////        );
//
//        t1.start();
//        t2.start();
//
//
//
//
////        var temp = new Thread(() -> {
////            try {
////                Thread.sleep(10000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////        }, "master");
//
////        temp.start();
////        temp.start();
//
//    }
}
