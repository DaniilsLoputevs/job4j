package ru.job4j.ref;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        cache.add(user);
        Thread first = new Thread(
                () -> {
                    user.setName("rename");
                    cache.add(User.of("new from Thread"));
                    System.out.println("first thread findAll:");
                    cache.findAll().forEach(System.out::println);
                }
        );
        first.start();
        first.join();
        System.out.println("main thread findAll:");
        cache.findAll().forEach(System.out::println);
    }
}
