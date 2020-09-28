package ru.job4j.extra.future_task_example;

import java.util.concurrent.*;

public class FutureTaskExample {
//    CallableDelay[] callable;
    FutureTask<String>[] futureTask;
    ExecutorService executor;
    private final int THREAD_COUNT = 3;

    /* ----------------------------------------------------- */
    public FutureTaskExample() {
//        this.callable = new CallableDelay[THREAD_COUNT];
        this.futureTask = new FutureTask[THREAD_COUNT];
        this.executor = Executors.newFixedThreadPool(THREAD_COUNT);
//        this.executor = Executors.

        for (int i = 0; i < THREAD_COUNT; i++) {
//            this.callable[i] = new CallableDelay(1000, (i + 1));
//            this.futureTask[i] = new FutureTask<>(callable[i]);
            this.futureTask[i] = new FutureTask<>(new CallableDelay(1000, (i + 1)));
            this.executor.execute(futureTask[i]);
        }
    }

    /* ----------------------------------------------------- */
    private boolean areTasksDone() {
        boolean isDone = true;
        for (int i = 0; i < THREAD_COUNT; i++) {
            if (!futureTask[i].isDone()) {
                isDone = false;
                break;
            }
        }
        return isDone;
    }

    /* ----------------------------------------------------- */

    /**
     * Цикл работы executor'а
     */
    public void forMainRun() {
        while (true) {
            try {
                // Завершение работы executor'а
                if (areTasksDone()) {

                    executor.shutdown();
                    System.out.println("\nexecutor shutdown");
                    return;
                }

                // Проверка завершения выполнения задачи 1-м потоком
                if (!futureTask[0].isDone()) {
                    System.out.println("1-ый поток завершен : " + futureTask[0].get());
                }

                System.out.println("Ожидание завершения 2-го потока");
                String txt = futureTask[1].get(200L, TimeUnit.MILLISECONDS);
                if (txt != null) {
                    System.out.println("2-ой поток завершен : " + txt);
                }

                System.out.println("Проверка завершения 3-го потока");
                if (futureTask[2].isCancelled()) {
                    System.out.println("3-ой поток был прерван ...");

                } else if (!futureTask[2].isDone()) {
                    txt = futureTask[2].get();
                    System.out.println("3-ий поток завершен : " + txt);
                }
                Thread.sleep(200);
            } catch (InterruptedException |
                    ExecutionException e) {
                System.err.println(e.getMessage());
            } catch (TimeoutException e) {
                /*
                 *  2-ой поток вызывает TimeoutException,
                 *  если задача не завершена за указанное
                 * время
                 */
                System.err.println("TimeoutException");
            }
        }
    }


    /* ----------------------------------------------------- */
    class CallableDelay implements Callable<String> {
        private long delay;
        private int idx;
        private int cycle;

        public CallableDelay(int delay, int idx) {
            this.delay = delay;
            this.idx = idx;
            this.cycle = idx;
        }

        @Override
        public String call() throws Exception {
            while (cycle > 0) {
                Thread.sleep(delay);
                cycle--;
                if (idx == 2 && cycle > 0) {
                    futureTask[futureTask.length - 1]
                            .cancel(true);
                }
            }
            /*
             * Идентификатор и наименование потока,
             * выполняющего данную callable задачу
             */
            return "" + idx + ". "
                    + Thread.currentThread().getName();
        }
    }


    //-----------------------------------------------------
    public static void main(String[] args) {
//        new FutureTaskExample();
        new FutureTaskExample().forMainRun();
    }
}
