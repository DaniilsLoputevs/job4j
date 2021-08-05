package ru.job4j.waitnotify;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        final Thread producer = new Thread(
                () -> {
                    for (int index = 1; index <= 3; index++) {
                        try {
                            System.out.println("producer - offer(): " + index);
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                    System.out.println("producer - FINISH ALL");
                }, "producer"

        );

        final Thread consumer = new Thread(
                () -> {
                    while (producer.isAlive()) {
                        try {
                            System.out.println("consumer - poll(): " + queue.poll());
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                    System.out.println("consumer - FINISH ALL");
                }, "consumer"
        );

        System.out.println("### Program Start ###");

        System.out.println(consumer.getName() + " - STARTED");
        consumer.start();
        System.out.println(producer.getName() + " - STARTED");
        producer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("### Program Finish ###");
    }

//    private static void timeout(Thread thread, int ms) {
//        var run = true;
//        var startTime = new GregorianCalendar().getTimeInMillis() + ms;
//        while (run) {
//            if (System.currentTimeMillis() > startTime) {
//                thread.interrupt();
//            }
//        }
//    }
}
