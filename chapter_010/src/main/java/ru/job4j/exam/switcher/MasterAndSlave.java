package ru.job4j.exam.switcher;

public class MasterAndSlave {

    public static void main(String[] args) throws Exception {

        MasterSlaveBarrier barrier = new MasterSlaveBarrier();

        Thread first = new Thread(
                () -> {
                    int index = 0;
                    while (index < 10) {
                        barrier.tryMaster();
                        index++;
                    }
                }
        );

        Thread second = new Thread(
                () -> {
                    int index = 0;
                    while (index < 10) {
                        barrier.trySlave();
                        index++;
                    }
                }
        );

        first.start();
        second.start();
    }

    public static class MasterSlaveBarrier {

        public void tryMaster() {
            synchronized (this) {
                try {
                    this.doneMaster();
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void trySlave() {
            synchronized (this) {
                try {
                    this.wait();
                    this.doneSlave();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void doneMaster() {
            System.out.println("Thread A");
            this.notifyAll();
        }

        public void doneSlave() {
            System.out.println("Thread B");
            this.notifyAll();
        }
    }
}

