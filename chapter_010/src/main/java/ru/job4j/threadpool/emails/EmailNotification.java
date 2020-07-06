package ru.job4j.threadpool.emails;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ThreadSafe
public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );


    public void emailTo(User user) {
        pool.submit(() -> {
            var subject = "Notification " + user.getUserName()
                    + " to email " + user.getEmail() + '.';
            var body = "Add a new event to " + user.getUserName();
            send(subject, body, user.getEmail());
        });
    }

    public void send(String subject, String body, String email) {
        // -sudo stub (empty methods - just stub)
        System.out.printf("%s%n%s%n%s%n %n", subject, body, email);
    }

    public void close() {
        pool.shutdown();
    }

    public boolean isFinished() {
        return pool.isTerminated();
    }

}
