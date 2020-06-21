package ru.job4j.ref;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("from main thread");
        cache.add(user);
        Thread first = new Thread(
                () -> {
                    user.setName("rename");
                    cache.add(User.of("from new thread"));
                    System.out.println("first thread findAll:");
                    cache.findAll().forEach(System.out::println);
                }
        );
        first.start();
        first.join();
        cache.add(User.of("main - one more"));
        cache.add(User.of("main - two more"));

        System.out.println("main thread findAll:");
        cache.findAll().forEach(System.out::println);
    }
}
