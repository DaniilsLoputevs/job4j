package ru.job4j.threadpool;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    private final AtomicBoolean work = new AtomicBoolean(true);

    public ThreadPool() {
        int threadCount = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < threadCount; i++) {
            threads.add(new Thread(
                    () -> {
                        while (work.get()) {
                            var temp = tasks.poll();
                            if (temp != null) {
                                temp.run();
                            }
                        }
                    }, "Thread pool: thread " + i
            ));
        }
        threads.forEach(Thread::start);
    }


    /**
     * add Thread_Task in {@code tasks}.
     *
     * @param job - Thread_Task.
     */
    public void work(Runnable job) {
        tasks.offer(job);
    }

    /**
     * Determinate all thread in {ThreadPool varName} and finish work.
     * <p>
     * * {@code tasks.offer(null);} - it's for notify all inner threads.
     */
    public void shutdown() {
        System.out.println("Thread pool: shutdown");
        work.set(false);
        threads.forEach(Thread::interrupt);
    }

    public void showAllThreadsState() {
        System.out.println("Thread pool: all threads state:");
        threads.forEach(thread -> System.out.printf("%s %s %s%n",
                thread.getName(), '-', thread.getState()));
    }

    /**
     * Thread where you control {ThreadPool varName} will wait until all threads
     * in {ThreadPool varName} will they finish work and went into WAITING state.
     */
    public void waitUntilFinishWork() {
        for (var thread : threads) {
            while (thread.getState() != Thread.State.WAITING) {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
